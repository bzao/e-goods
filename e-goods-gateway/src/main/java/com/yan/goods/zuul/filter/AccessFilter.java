package com.yan.goods.zuul.filter;

import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.yan.goods.zuul.jwt.JwtConfig;
import com.yan.goods.zuul.jwt.JwtUtils;

@Component
public class AccessFilter extends ZuulFilter {
	
	private final static Logger logger = LoggerFactory.getLogger(AccessFilter.class);
	
    @Autowired
    private JwtUtils jwtUtils;
    
    @Autowired
    private JwtConfig jwtConfig;
    
	/**
	 * 权限验证
	 */
	@Override
	public Object run() {
		RequestContext rc = RequestContext.getCurrentContext();
		HttpServletRequest request = rc.getRequest();
		logger.info("method={},url={}",request.getMethod(),request.getRequestURL().toString());
		String url = request.getRequestURL().toString();
//		if (url.contains("goods-user/user/getToken")) {
		if (url.contains("goods-user/user/getToken") || url.contains("/goods-order")) {
			logger.info("----------获取token直接放开----------");
			return null;
		}
		logger.info("----------pre----------");
        //获取用户凭证
        String token = request.getHeader(jwtConfig.getHeader());
        if(StringUtils.isBlank(token)){
            token = request.getParameter(jwtConfig.getHeader());
        }
		logger.info("----------token："+token);
        //凭证为空
        if(StringUtils.isBlank(token)){
			logger.warn("token is null.......");
			rc.setSendZuulResponse(false);// 结束请求，不再继续下级传递，不让路由继续了
			rc.setResponseStatusCode(401);
			rc.setResponseBody("{\"result\":\"token is null\"}");
			rc.getResponse().setContentType("text/html;charset=utf-8");
        } else {
            Claims claims = jwtUtils.getClaimByToken(token);
            if(claims == null || jwtUtils.isTokenExpired(claims.getExpiration())){
    			logger.warn("token is 失效，请重新登录.......");
    			rc.setSendZuulResponse(false);// 结束请求，不再继续下级传递，不让路由继续了
    			rc.setResponseStatusCode(401);
    			rc.setResponseBody("{\"result\":\"token is invalid\"}");
    			rc.getResponse().setContentType("text/html;charset=utf-8");
//                throw new RRException(jwtUtils.getHeader() + "失效，请重新登录", HttpStatus.UNAUTHORIZED.value());
            } else {
            	logger.info("token is Ok");
            }
        }
        
		return null;
	}

	// 是否开启
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * 过滤器级别
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	// 过滤器的类型
	@Override
	public String filterType() {
		return "pre";
	}

}