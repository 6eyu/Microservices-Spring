package com.learn.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AuthPreFilter extends ZuulFilter {

//    @Autowired
//    private StringRedisTemplate redisTemplate;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public Object run() {
        logger.debug("My customized pre filter");
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String reqUri = request.getRequestURI();

        //将允许直接访问的URL加入Array里
        String[] permitList = new String[]{
                "/oauth/token"
        };

        boolean isPermited = false;

        if(stringContainsItemFromList(reqUri, permitList)
                || reqUri.contains("signin")
                || reqUri.contains("oauth/token")
                || reqUri.contains("static")
                || reqUri.contains("crossSiteMessage")){
            logger.debug("Request URI matched the pattern of permitList");
            return null;
        } else if (reqUri.contains("eiz-account/static")) {
            return null;
        } else if (reqUri.contains("lumen")) {
            ctx.addZuulRequestHeader("Authorization", "Basic " + request.getHeader("Authorization"));
            return null;
        } else {
            String token = request.getHeader("Authorization");
            logger.debug("get Authorization header: "+ token);

            if (StringUtils.isEmpty(token) || !token.startsWith("Bearer ")) {
                ctx.setResponseStatusCode(401);
                ctx.setSendZuulResponse(false);
                logger.debug("Http Request doesn't contain a header of Authorization or Bearer");
            } else {
                token = token.replace("Bearer ", "");
                logger.debug("took off \"Bearer\" from token string");
            }

            try {

                logger.debug("assembling RestTemplate to check token endpoint");
                RestTemplate restTemplate = new RestTemplate();

                String checkTokenEndpoint = "http://localhost:8089/oauth/check_token?token=" + token;

                ResponseEntity<Boolean> response = restTemplate.getForEntity(checkTokenEndpoint,Boolean.class);

                if(response.getBody() == null) {
                    throw new NullPointerException("response's body has null pointer exception");
                } else {
                    isPermited = response.getBody();
                }
            }
            catch (Exception e) {
                logger.debug("check_token endpoint returns an exception");
                ctx.setResponseStatusCode(401);
                ctx.setSendZuulResponse(false);
                ctx.setResponseBody(String.valueOf(e));
            }
        }

        if(isPermited) {
            logger.debug("request has been authorized with a valid access token");

            // *********** 连接 redis ×××××××××××

//            String id = request.getHeader("id");
//            logger.debug("get header ID: " + id);
//
//
//
//            if( id == null) return null;
//
//
//            if (redisTemplate.hasKey(id)) {
//                ctx.setResponseStatusCode(401);
//                ctx.setSendZuulResponse(false);
//            } else {
//                logger.debug("set id into redis");
//                redisTemplate.opsForValue().set(id, "0",500, TimeUnit.SECONDS);
//
//            }

            // *********** 连接 redis ×××××××××××

            return null;
        }else {
            ctx.setResponseStatusCode(401);
            ctx.setSendZuulResponse(false);
        }

        return null;
    }

    public static boolean stringContainsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).parallel().anyMatch(inputStr::contains);
    }
}
