package com.sakura.study.controller;

import com.sakura.study.model.Region;
import com.sakura.study.service.RegionService;
import com.sakura.study.utils.Qiniu;
import com.sakura.study.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommonController {

    @Autowired
    RegionService regionService;
    @Autowired
    Qiniu qiniu;

    /**
     * 获取父级地区地址
     * @return
     */
    @RequestMapping(value = "/region/parentRegions",method = RequestMethod.GET)
    public ResponseResult parentRegions(){
        return ResponseResult.success(regionService.getParentRegion());
    }

    /**
     * 获取子地址
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/region/parent/{parentId}",method = RequestMethod.GET)
    public ResponseResult getSubRegions(@PathVariable("parentId") Integer parentId){
        List<Region> regions = regionService.getSubRegion(parentId);
        return ResponseResult.success(regions);
    }

    /**
     * 获取七牛上传token
     * @return
     */
    @RequestMapping(value = "/qiniu/token",method = RequestMethod.GET)
    public ResponseResult getQiniuToken(){
        String token = qiniu.getUploadToken();
        return ResponseResult.success(token);
    }
}
