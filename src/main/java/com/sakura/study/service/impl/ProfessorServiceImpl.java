package com.sakura.study.service.impl;

import com.sakura.study.dao.ProfessorMapper;
import com.sakura.study.dto.PageRequest;
import com.sakura.study.dto.ProfessorDto;
import com.sakura.study.model.Employee;
import com.sakura.study.model.Professor;
import com.sakura.study.service.ProfessorService;
import com.sakura.study.utils.BusinessException;
import com.sakura.study.utils.CopyUtils;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService{

    @Autowired
    private ProfessorMapper professorMapper;
    /**
     * 添加教授
     *
     * @param professor
     * @param employee
     * @return
     */
    @Override
    public Professor add(Professor professor, Employee employee) {
        professorMapper.insertSelective(professor);
        return professor;
    }

    /**
     * 删除教授
     *
     * @param id
     * @param employee
     */
    @Override
    public void delete(Integer id, Employee employee) {
        Professor professor = getProfessorById(id);
        professor.setDeleted(true);
        professorMapper.updateByPrimaryKeySelective(professor);
    }

    /**
     * 修改教授信息
     *
     * @param professor
     * @param employee
     * @return
     */
    @Override
    public void edit(Professor professor, Employee employee) {
        Professor data = getProfessorById(professor.getId());
        CopyUtils.copyProperties(professor,data);
        professorMapper.updateByPrimaryKeySelective(data);
    }

    /**
     * 获取教授信息的分页列表
     *
     * @param page
     * @return
     */
    @Override
    public ResponseResult getPage(PageRequest page) {
        List<ProfessorDto> data = professorMapper.getPageProfessor(page);
        int dataCount = professorMapper.getPageProfessorCount();
        return ResponseResult.pageResult(data,dataCount);
    }

    /**
     *获取信息根据id
     * @param id
     * @return
     */
    private Professor getProfessorById(Integer id){
        Professor professor = professorMapper.selectByPrimaryKey(id);
        if(professor == null || professor.getDeleted())
            throw new BusinessException(400,"教授信息不存在");
        return professor;
    }
}
