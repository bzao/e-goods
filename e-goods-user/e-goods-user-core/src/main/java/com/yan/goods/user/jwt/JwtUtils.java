package com.yan.goods.user.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yan.goods.user.persistence.UserMapper;
import java.util.Date;

/**
 * jwt工具类
 */
@Component
public class JwtUtils {
    private final static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Autowired
    private JwtConfig jwtConfig;
    
    @Autowired
    private UserMapper userMapper;
    /**
     * 生成jwt token
     */
    public String generateToken(String userName, String passWord) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + jwtConfig.getExpire() * 1000);
        Integer userId = userMapper.login(userName, passWord);
        if (null==userId) {
        	logger.info("userName:"+userName+"passWord:"+passWord+",获取token失败！");
//        	throw new RuntimeException("listexception");
        	return null;
        }
        logger.info("userName:"+userName+"passWord:"+passWord+",本次使用的超时时间是:"+jwtConfig.getExpire()+",加密secret是："+jwtConfig.getSecret());
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId+"")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
    }
}
