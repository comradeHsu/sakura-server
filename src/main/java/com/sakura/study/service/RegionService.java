package com.sakura.study.service;

import com.sakura.study.model.Region;

import java.util.List;

public interface RegionService {
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
