package com.sakura.study.service.impl;

import com.sakura.study.dao.RegionMapper;
import com.sakura.study.model.Region;
import com.sakura.study.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServiceImpl implements RegionService{

    @Autowired
    RegionMapper regionMapper;


    /**
     * 获取父级地址
     *
     * @return
     */
    @Override
    public List<Region> getParentRegion() {
        return regionMapper.getParentRegion();
    }

    /**
     * 获取子级地址
     *
     * @param parentId
     * @return
     */
    @Override
    public List<Region> getSubRegion(Integer parentId) {
        return regionMapper.getSubRegion(parentId);
    }
}
