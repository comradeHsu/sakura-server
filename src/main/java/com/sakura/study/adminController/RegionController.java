package com.sakura.study.adminController;

import com.sakura.study.model.Region;
import com.sakura.study.service.RegionService;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/region")
public class RegionController {

    @Autowired
    RegionService regionService;

    /**
     * 获取父级地区地址
     * @return
     */
    @RequestMapping(value = "/parentRegions",method = RequestMethod.GET)
    public ResponseResult parentRegions(){
        return ResponseResult.success(regionService.getParentRegion());
    }

    /**
     * 获取子地址
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/parent/{parentId}",method = RequestMethod.GET)
    public ResponseResult getSubRegions(@PathVariable("parentId") Integer parentId){
        List<Region> regions = regionService.getSubRegion(parentId);
        return ResponseResult.success(regions);
    }
}
