package com.yan.goods.commons;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 基于 httpclient 4.3.1版本的 http工具类
 * @author 
 *
 */
public class HttpClientHelper {
 
	private static final CloseableHttpClient httpClient;
	public static final String CHARSET = "UTF-8";
	
	private final static Logger LOGGER = LoggerFactory.getLogger(HttpClientHelper.class);
	
	static {
	        //socket连接30秒超时
	    RequestConfig config = RequestConfig.custom().setConnectTimeout(30000).setSocketTimeout(40000).build();
	    httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	}
 
    /**
     * HTTP Get 获取内容
     * @param url  请求的url地址 ?之前的地址
     * @param params 请求的参数
     * @return    页面内容
     */
    public static String doGet(String url,Map<String,String> params,Map<String, Object> headers){
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            if(params != null && !params.isEmpty()){
                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
                for(Map.Entry<String,String> entry : params.entrySet()){
                    String value = entry.getValue();
                    if(value != null){
                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
                    }
                }
                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, CHARSET));
            }
            HttpGet httpGet = new HttpGet(url);
            if(headers!=null&&!headers.isEmpty()){
                Set<String> keys = headers.keySet();
                for (Iterator<String> i = keys.iterator(); i.hasNext();) {
                    String key = i.next();
                    httpGet.addHeader(key, headers.get(key)+"");
                }
            }
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                httpGet.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
        }
        return null;
    }
     
    /**
     * HTTP Post 发送json数据
     * @param url  请求的url地址 
     * @param params 请求的参数
     * @return    页面内容
     */
    public static String doPost(String url, String json, Map<String, Object> headers){
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            HttpPost httpPost = new HttpPost(url);
            
            httpPost.addHeader("Content-Type", "application/json; charset=utf-8");
            if(headers!=null&&!headers.isEmpty()){
                Set<String> keys = headers.keySet();
                for (Iterator<String> i = keys.iterator(); i.hasNext();) {
                    String key = i.next();
                    httpPost.addHeader(key, headers.get(key)+"");
                }
            }
            
  	       if (StringUtils.isNotBlank(json)) {
  	    	   // 创建请求内容
  	    	   StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
  	    	   httpPost.setEntity(entity);
 	       }
            
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * HTTP put 发送json数据
     * @param url  请求的url地址 
     * @param headers 请求头
     * @return    页面内容
     */
    public static String doPut(String url, String json, Map<String, Object> headers){
    	if(StringUtils.isBlank(url)){
    		return null;
    	}
    	try {
//            if(params != null && !params.isEmpty()){
//                List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
//                for(Map.Entry<String,String> entry : params.entrySet()){
//                    String value = entry.getValue();
//                    if(value != null){
//                        pairs.add(new BasicNameValuePair(entry.getKey(),value));
//                    }
//                }
//                url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, CHARSET));
//            }
            LOGGER.info("调接口=>"+url);
            HttpPut httpPut = new HttpPut(url);
//            httpPut.getParams().setParameter(params.get(""), value);
    		httpPut.addHeader("Content-Type", "application/json; charset=utf-8");
    		if(headers!=null&&!headers.isEmpty()){
    			Set<String> keys = headers.keySet();
    			for (Iterator<String> i = keys.iterator(); i.hasNext();) {
    				String key = i.next();
    				httpPut.addHeader(key, headers.get(key)+"");
    			}
    		}
    		
    		if (StringUtils.isNotBlank(json)) {
    			// 创建请求内容
    			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
    			httpPut.setEntity(entity);
    		}
    		
    		CloseableHttpResponse response = httpClient.execute(httpPut);
    		int statusCode = response.getStatusLine().getStatusCode();
    		
    		if (statusCode != 200) {
    			httpPut.abort();
    			throw new RuntimeException("HttpClient,error status code :" + statusCode);
    		}
    		HttpEntity entity = response.getEntity();
    		String result = null;
    		if (entity != null){
    			result = EntityUtils.toString(entity, "utf-8");
    		}
    		EntityUtils.consume(entity);
    		response.close();
    		return result;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    /**
     * HTTP Post 发送json数据到paython服务器
     * @param url  请求的url地址 
     * @param params 请求的参数
     * @return    页面内容
     */
    public static String doPostPaython(String url, String json, Map<String, Object> headers){
        if(StringUtils.isBlank(url)){
            return null;
        }
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
            httpPost.addHeader("Accept", "application/json, text/plain, */*");
            if(headers!=null&&!headers.isEmpty()){
                Set<String> keys = headers.keySet();
                for (Iterator<String> i = keys.iterator(); i.hasNext();) {
                    String key = i.next();
                    httpPost.addHeader(key, headers.get(key)+"");
                }
            }
            
  	       if (StringUtils.isNotBlank(json)) {
  	    	   // 创建请求内容
  	    	   StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
  	    	   httpPost.setEntity(entity);
 	       }
            
            CloseableHttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200 && statusCode != 406 && statusCode != 412) {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null){
                result = EntityUtils.toString(entity, "utf-8");
            }
            EntityUtils.consume(entity);
            response.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}