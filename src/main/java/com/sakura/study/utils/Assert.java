package com.sakura.study.utils;

import org.springframework.util.StringUtils;

public class Assert {


    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new BusinessException(400,message);
        }
    }

    public static void moreThanZero(Integer num, String message) {
        if (num <= 0){
            throw new BusinessException(400,message);
        }
    }

    public static void allMoreThanZero(String message, Integer ...nums) {
        for(Integer num : nums) {
            if (num <= 0) {
                throw new BusinessException(400, message);
            }
        }
    }

    public static void notThanZero(int num, String message){
        if(num > 0){
            throw new BusinessException(400, message);
        }
    }

    public static void isEquals(Object object,Object other, String message){
        if(!object.equals(other)){
            throw new BusinessException(400, message);
        }
    }

    public static void notEmpty(String string,String message){
        if(StringUtils.isEmpty(string)){
            throw new BusinessException(400, message);
        }
    }
}
