package com.sakura.study.utils;

import com.qiniu.util.Auth;

public class Qiniu {

    private static final String accessKey = "";

    private static final String secretKey = "";

    private static final String bucket = "sakura_picture";

    /**
     * 获取七牛上传凭证
     * @return
     */
    public static String getUploadToken(){
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }
}
