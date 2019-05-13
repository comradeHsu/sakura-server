package com.sakura.study.config;

import com.google.common.cache.LoadingCache;
import com.sakura.study.model.User;
import com.sakura.study.utils.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class ApiInterceptor extends HandlerInterceptorAdapter {

    @Resource(name = "userCache")
    LoadingCache<String, Optional<User>> userCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Token");
        if(StringUtils.isEmpty(token)) {
            return true;
        }
        User cacheUser = userCache.getUnchecked(token).orElse(null);
        if(cacheUser == null){
            throw new BusinessException(403,"登陆已过期");
        }
        return true;
    }
}
