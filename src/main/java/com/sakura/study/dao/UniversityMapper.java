package com.sakura.study.dao;

import com.sakura.study.model.University;
import org.springframework.data.repository.query.Param;

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
    int getPageSchoolsCount(String schoolName);
}