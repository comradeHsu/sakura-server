package com.sakura.study.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sakura.study.model.Employee;
import com.sakura.study.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {
//用户信息缓存，类似session
    @Bean(name = "employeeCache")
    public LoadingCache<String, Optional<Employee>> buildCache(){
        return CacheBuilder.newBuilder()
                .expireAfterAccess(30, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Optional<Employee>>() {
                    @Override
                    public Optional<Employee> load(String s){
                        return Optional.ofNullable(null);
                    }
                });
    }

    @Bean(name = "userCache")
    public LoadingCache<String, Optional<User>> buildUserCache(){
        return CacheBuilder.newBuilder()
                .expireAfterAccess(60, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Optional<User>>() {
                    @Override
                    public Optional<User> load(String s){
                        return Optional.ofNullable(null);
                    }
                });
    }
}
