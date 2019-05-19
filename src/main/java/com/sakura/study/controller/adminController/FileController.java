package com.sakura.study.controller.adminController;

import com.sakura.study.dao.UploadFileMapper;
import com.sakura.study.dto.FileReq;
import com.sakura.study.model.UploadFile;
import com.sakura.study.utils.ResponseResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/file")
public class FileController {
    @Autowired
    UploadFileMapper fileMapper;

    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    public Object UploadDFile(UploadFile fileInfo) {
        fileInfo.setCreateTime(new Date());
        fileInfo.setUpdateTime(new Date());
        fileMapper.insertSelective(fileInfo);
        return ResponseResult.success("添加成功",null);
    }
    @RequestMapping(value = "/getFiles", method = RequestMethod.GET)
    public Object getFileList(FileReq fileReq) {
        fileReq.initSkip();
        List<UploadFile> data = fileMapper.getPage(fileReq);
        int dataCount = fileMapper.getCount(fileReq);
        return ResponseResult.pageResult(data, dataCount);
    }
}
