package com.yan.goods.zuul.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * jwt工具类
 */
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "yan.jwt")
public class JwtConfig {

    private String secret;
    private long expire;
    private String header;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}

