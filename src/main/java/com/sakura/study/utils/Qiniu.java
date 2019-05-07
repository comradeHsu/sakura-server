package com.sakura.study.utils;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Qiniu {

    @Value("qiniu.key")
    private  String accessKey;

    @Value("qiniu.")
    private  String secretKey = "";

    private  String bucket = "sakura_picture";

    /**
     * 获取七牛上传凭证
     * @return
     */
    public  String getUploadToken(){
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }
}
