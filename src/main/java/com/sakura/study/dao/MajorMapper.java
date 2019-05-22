package com.sakura.study.dao;

import com.sakura.study.dto.MajorPageRequest;
import com.sakura.study.model.Major;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MajorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Major record);

    int insertSelective(Major record);

    Major selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Major record);

    int updateByPrimaryKey(Major record);

    /**
     * 查找学校的专业信息，分页
     * @param request
     * @return
     */
    List<Major> getPageMajors(MajorPageRequest request);

    /**
     * 学校的专业数量
     * @param request
     * @return
     */
    int getPageMajorsCount(MajorPageRequest request);

    /**
     * 根据学校id和专业名字查找
     * @param universityId
     * @param majorName
     * @return
     */
    Major findByUniversityIdAndName(@Param("universityId") Integer universityId,@Param("majorName")String majorName);
}