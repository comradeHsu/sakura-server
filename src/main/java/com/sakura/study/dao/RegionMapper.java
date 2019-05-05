package com.sakura.study.dao;

import com.sakura.study.model.Region;

import java.util.List;

public interface RegionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);

    /**
     * 获取父级地址
     * @return
     */
    List<Region> getParentRegion();

    /**
     * 获取子级地址
     * @return
     */
    List<Region> getSubRegion(Integer parentId);
}