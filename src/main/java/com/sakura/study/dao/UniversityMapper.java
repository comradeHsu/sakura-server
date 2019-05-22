package com.sakura.study.dao;

import com.sakura.study.dto.UniversityPageRequest;
import com.sakura.study.model.University;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UniversityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(University record);

    int insertSelective(University record);

    University selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(University record);

    int updateByPrimaryKey(University record);

    /**
     * 获取分页的学校信息
     * @param schoolName
     * @param start
     * @param pageCount
     * @return
     */
    List<University> getPageSchools(@Param("schoolName") String schoolName, @Param("start")Integer start,
                                    @Param("pageCount")Integer pageCount);

    /**
     * 获取学校数量
     * @param schoolName
     * @return
     */
    int getPageSchoolsCount(@Param("schoolName")String schoolName);

    /**
     * 查找是否有重复名字
     * @param schoolName
     * @return
     */
    University findBySchoolName(@Param("schoolName")String schoolName);

    /**
     * 搜索学校
     * @param request
     * @return
     */
    List<University> search(UniversityPageRequest request);

    /**
     * 搜索学校数量
     * @param request
     * @return
     */
    int searchCount(UniversityPageRequest request);

    /**
     * 分页推荐学校
     * @param request
     * @return
     */
    List<University> getRecommend(UniversityPageRequest request);

    /**
     * 分页推荐学校总数
     * @param request
     * @return
     */
    int getRecommendCount(UniversityPageRequest request);
}