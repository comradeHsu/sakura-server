package com.sakura.study.config;

import com.google.common.cache.LoadingCache;
import com.sakura.study.dao.OperationLogMapper;
import com.sakura.study.model.Employee;
import com.sakura.study.model.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

@Component
public class InterceptorHttp  extends HandlerInterceptorAdapter {
    @Autowired
    @Qualifier("employeeCache")
    LoadingCache<String, Optional<Employee>> employeeCache;
    @Autowired
    OperationLogMapper operationLogMapper;

    OperationLog operationLog = null;

    Employee employee = null;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Token");
        if(StringUtils.isEmpty(token)) {
            return true;
        }
        Employee employee = employeeCache.getUnchecked(token).orElse(null);
        if(employee == null) {
            return true;
        }
        operationLog = new OperationLog();
        operationLog.setEmployeeId(employee.getId());
        operationLog.setContent(request.getRequestURI()+ ":" + request.getMethod());

        switch (request.getMethod()) {
            case "GET":
                operationLog.setOperation(0);
                break;
            case "POST":
                operationLog.setOperation(1);
                break;
            case "DELETE":
                operationLog.setOperation(3);
                break;
            case "PUT":
                operationLog.setOperation(2);
                break;

        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        if(operationLog != null) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            operationLog.setCreateTime(new Date());
            operationLog.setContent(operationLog.getContent() + ":" + handlerMethod.getMethod().getName());
            operationLogMapper.insert(operationLog);
            this.reset();
        }

    }

    private void reset() {
        operationLog = null;
        employee = null;
    }


}
