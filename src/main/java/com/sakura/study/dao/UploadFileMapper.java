package com.sakura.study.dao;

import com.sakura.study.dto.FileReq;
import com.sakura.study.model.UploadFile;
import com.sakura.study.model.UploadFileKey;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UploadFileMapper {
    int deleteByPrimaryKey(UploadFileKey key);

    int insert(UploadFile record);

    int insertSelective(UploadFile record);

    UploadFile selectByPrimaryKey(UploadFileKey key);

    int updateByPrimaryKeySelective(UploadFile record);

    int updateByPrimaryKey(UploadFile record);
    List<UploadFile> getPage(FileReq fileReq);
    int getCount(FileReq fileReq);
}