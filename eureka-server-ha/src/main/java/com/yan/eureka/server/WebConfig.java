package com.yan.eureka.server;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        更好的办法是,配置/eureka/**忽略csrf防御拦截,直接放行(/eureka/**是在application.yml中配置的eureka注册地址)
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}