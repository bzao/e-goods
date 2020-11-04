package com.yan.goods.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class ErrorFilter extends ZuulFilter{
	private final static Logger logger = LoggerFactory.getLogger(ErrorFilter.class);
	
	/**
	 * error
	 */
	@Override
	public Object run() {
		RequestContext rc = RequestContext.getCurrentContext();
		HttpServletRequest request = rc.getRequest();
		logger.info("----error---------");
		return null;
	}

	//是否开启
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	//过滤器的类型
	@Override
	public String filterType() {
		return "error";
	}

}
