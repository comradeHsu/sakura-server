package com.sakura.study.service.impl;

import com.google.common.cache.LoadingCache;
import com.sakura.study.dao.AssessmentMapper;
import com.sakura.study.dao.OperationLogMapper;
import com.sakura.study.dao.UserAgreementMapper;
import com.sakura.study.dao.UserMapper;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.dto.UserAgreementDto;
import com.sakura.study.dto.UserDto;
import com.sakura.study.model.*;
import com.sakura.study.service.UserService;
import com.sakura.study.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    AssessmentMapper assessmentMapper;

    @Autowired
    private UserAgreementMapper userAgreementMapper;

    @Resource(name = "employeeCache")
    private LoadingCache<String, Optional<Employee>> employeeCache;

    @Autowired
    private OperationLogMapper operationLogMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 获取分页的用户列表
     *
     * @param page
     * @return
     */
    @Override
    public ResponseResult getPageUsers(PageRequest page,String realName) {
        List<User> users = userMapper.getPageUser(page.getSkip(),page.getPageCount(),realName);
        int dataCount = userMapper.getPageUserCount(realName);
        return ResponseResult.pageResult(users,dataCount);
    }

    /**
     * 获取家列表，可搜索
     *
     * @param username
     * @return
     */
    @Override
    public List<User> getParents(String username) {
        return userMapper.getParents(username);
    }

    /**
     * 添加用户
     *
     * @param token
     * @param user
     * @return
     */
    @Override
    @Transactional
    public User add(String token, User user) {
        getParentByParentId(user);
        user.setPassword(MD5Util.md5Encode(user.getPassword()));
        userMapper.insertSelective(user);
        Employee self = employeeCache.getUnchecked(token).orElse(null);
        OperationLog operationLog = buildLog(user,Operation.ADD,self);
        operationLogMapper.insertSelective(operationLog);
        return user;
    }

    /**
     * 修改用户
     *
     * @param token
     * @param user
     */
    @Override
    @Transactional
    public void edit(String token, User user) {
        User record = getUserById(user.getId());
        getParentByParentId(user);
        userMapper.updateByPrimaryKeySelective(user);
        Employee self = employeeCache.getUnchecked(token).orElse(null);
        OperationLog operationLog = buildLog(record,Operation.EDIT,self);
        operationLogMapper.insertSelective(operationLog);
    }

    /**
     * 删除用户
     * @param token
     * @param id
     */
    @Override
    @Transactional
    public void delete(String token, Integer id) {
        User user = getUserById(id);
        user.setDeleted(true);
        userMapper.updateByPrimaryKeySelective(user);
        if(user.getParentId() != 0) {
            int count = userMapper.resetParentId(user.getParentId());
            logger.info("重置了{}为用户的家长信息",count);
        }
        Employee self = employeeCache.getUnchecked(token).orElse(null);
        OperationLog operationLog = buildLog(user,Operation.DELETE,self);
        operationLogMapper.insertSelective(operationLog);
    }

    /**
     * 获取分页的协议
     *
     * @param page
     * @return
     */
    @Override
    public ResponseResult getAgreements(PageRequest page) {
        List<UserAgreementDto> data = userAgreementMapper.getAgreements(page);
        int dataCount = userAgreementMapper.getAgreementCount();
        return ResponseResult.pageResult(data,dataCount);
    }

    /**
     * 修改用户流程
     *
     * @param token
     * @param user
     */
    @Override
    @Transactional
    public void editProcess(String token, User user) {
        User record = getUserById(user.getId());
        userMapper.updateByPrimaryKeySelective(user);
        Employee self = employeeCache.getUnchecked(token).orElse(null);
        OperationLog operationLog = buildLog(record,Operation.EDIT,self);
        operationLogMapper.insertSelective(operationLog);
    }

    /**
     * api
     * 用户登录
     *
     * @param user
     * @return
     */
    @Override
    public UserDto login(User user) {
        User record = userMapper.findByUsername(user.getUsername());
        if(record == null)
            throw new BusinessException(404,"用户不存在");
        if(!MD5Util.md5Encode(user.getPassword()).equals(record.getPassword()))
            throw new BusinessException(400,"用户名或密码错误");
        UserDto data = new UserDto();
        BeanUtils.copyProperties(record,data);
        Assessment assessment = assessmentMapper.selectByUserId(record.getId());
        if(assessment != null) data.setAssessed(true);
        return data;
    }

    /**
     * api
     * 获取用户详细信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserDto getUserInfo(Integer userId) {
        UserDto user = userMapper.getUserInfo(userId);
        if(user == null) throw new BusinessException(404,"此用户不存在或已删除");
        Assessment assessment = assessmentMapper.selectByUserId(userId);
        if(assessment != null) user.setAssessed(true);
        return user;
    }

    /**
     * 用户注册
     * api
     * @param userDto
     * @return
     */
    @Override
    public UserDto register(UserDto userDto) {
        if(!StringUtils.isEmpty(userDto.getParentPhone())){
            User parent = userMapper.findByPhone(userDto.getParentPhone());
            if(parent == null) throw new BusinessException(400,"家长账号不存在");
            userDto.setParentId(parent.getId());
        }
        if(!StringUtils.isEmpty(userDto.getUsername())){
            User record = userMapper.findByUsername(userDto.getUsername());
            if(record != null) throw new BusinessException(400,"此账号已存在");
        } else {
            userDto.setUsername(userDto.getPhoneNumber());
        }
        User user = userMapper.findByPhone(userDto.getPhoneNumber());
        if(user != null) throw new BusinessException(400,"此手机号已被使用");
        if(StringUtils.isEmpty(userDto.getRealName())) userDto.setRealName("用户"+(int)(Math.random() * 100000));
        userDto.setPassword(MD5Util.md5Encode(userDto.getPassword()));
        userMapper.insertSelective(userDto);
        return userDto;
    }

    /**
     * api
     * 用户修改自己的信息
     *
     * @param user
     * @return
     */
    @Override
    public void editInfo(User user) {
        getUserById(user.getId());
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 没有则抛出异常
     * @param id
     */
    @Override
    public User getUserById(Integer id) {
        User record = userMapper.selectByPrimaryKey(id);
        if(record == null || record.getDeleted()) throw new BusinessException(404,"此用户不存在或已删除");
        return record;
    }

    /**
     * api
     * 用户评估
     *
     * @param assessment
     * @param userId
     */
    @Override
    public void assessment(Assessment assessment, Integer userId) {
        int totalScore = 0;
        switch (assessment.getSchoolType()){
            case 0:
                totalScore += 40;
                break;
            case 1:
                totalScore += 32;
                break;
            case 2:
                totalScore += 24;
                break;
        }
        int toefl = assessment.getToefl();
        if(110 <= toefl && toefl <= 120){
            totalScore = totalScore + 20;
        } else if(100 <= toefl && toefl <= 109){
            totalScore = totalScore + 16;
        } else if(90 <= toefl && toefl <= 99){
            totalScore = totalScore + 12;
        } else if(toefl < 90){
            totalScore = totalScore + 8;
        }
        int japeneseLevel = assessment.getJapaneseLevel();
        if(japeneseLevel == 1){
            totalScore += 20;
        } else if(japeneseLevel == 2){
            totalScore += 14;
        }
        int gpa = assessment.getGpa();
        if(assessment.getSchoolGpa() == 5){
            switch (gpa){
                case 5:
                    totalScore += 10;
                    break;
                case 4:
                    totalScore += 8;
                    break;
                case 3:
                    totalScore += 8;
                    break;
                case 2:
                    totalScore += 5;
                    break;
                case 1:
                    totalScore += 5;
                    break;
            }
        } else if(assessment.getSchoolGpa() == 4){
            switch (gpa){
                case 4:
                    totalScore += 10;
                    break;
                case 3:
                    totalScore += 8;
                    break;
                case 2:
                    totalScore += 5;
                    break;
                case 1:
                    totalScore += 5;
                    break;
            }
        }
        int score = assessment.getScore();
        switch (score){
            case 1:
                totalScore += 10;
                break;
            case 2:
                totalScore += 6;
                break;
            case 3:
                totalScore += 4;
                break;
        }
        assessment.setTotalScore(totalScore);
        assessment.setUserId(userId);
        assessmentMapper.deleteByUserId(userId);
        assessmentMapper.insertSelective(assessment);
    }

    /**
     * 用户上传协议
     *
     * @param agreement
     * @param user
     */
    @Override
    public void uploadAgreement(UserAgreement agreement, User user) {
        UserAgreement data = userAgreementMapper.selectByUserId(agreement.getUserId());
        User dataUser = userMapper.selectByPrimaryKey(agreement.getUserId());
        if(dataUser == null || dataUser.getDeleted()) throw new BusinessException(404,"用户不存在");
        if(dataUser.getUserType() == 1 && dataUser.getAge() < 18){
            if(user.getId().equals(agreement.getUserId())){
                throw new BusinessException(405,"你还未成年不能签署协议");
            }
            if(!user.getId().equals(dataUser.getParentId())){
                throw new BusinessException(405,"您不是对方家长不能签署协议");
            }
            saveOrUpdate(agreement, data);
            return;
        }
        saveOrUpdate(agreement,data);
    }

    /**
     * 获取孩子列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<User> getChildren(Integer userId) {
        return userMapper.getChildrens(userId);
    }

    /**
     * 插入或修改
     * @param agreement
     * @param data
     */
    private void saveOrUpdate(UserAgreement agreement, UserAgreement data) {
        if(data == null){
            userAgreementMapper.insertSelective(agreement);
        } else {
            CopyUtils.copyProperties(agreement,data);
            userAgreementMapper.updateByPrimaryKeySelective(data);
        }
    }

    /**
     * 检查家长账号有效性
     * @param user
     */
    private void getParentByParentId(User user) {
        if(user.getParentId() != 0){
            try {
                getUserById(user.getParentId());
            } catch (BusinessException e){
                throw new BusinessException(404,"家长账号不存在或已删除");
            }
        }
    }

    private OperationLog buildLog(User record, Operation operation, Employee employee){
        OperationLog operationLog = new OperationLog();
        operationLog.setOperation(operation.getValue());
        operationLog.setContent(buildContent(record,operation.getDesc()));
        operationLog.setEmployeeId(employee.getId());
        return operationLog;
    }

    private String buildContent(User record, String method){
        return method +"用户"+ record.getUsername();
    }
}
