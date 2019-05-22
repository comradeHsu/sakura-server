package com.sakura.study.config;

import com.google.common.cache.LoadingCache;
import com.sakura.study.dao.OperationLogMapper;
import com.sakura.study.model.Employee;
import com.sakura.study.model.OperationLog;
import com.sakura.study.utils.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

@Component
public class InterceptorHttp  extends HandlerInterceptorAdapter {

    @Resource(name = "employeeCache")
    LoadingCache<String, Optional<Employee>> employeeCache;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Token");
        if(StringUtils.isEmpty(token)) {
            throw new BusinessException(403,"登陆已过期");
        }
        Employee employee = employeeCache.getUnchecked(token).orElse(null);
        if(employee == null) {
            throw new BusinessException(403,"登陆已过期");
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception { }
}
