package com.yr.yrv1annotation.interceptor;

import com.yr.yrv1annotation.config.ResponseResult;
import com.yr.yrv1annotation.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    // 标记名称
    public static final String RESPONSE_RESULT = "RESPONSE_RESULT";

    // 是否请求 包含了 包装注解 标记， 没有就直接返回， 不需要重写返回体
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // 判断请求 是否有包装标记
        ResponseResult responseResult = (ResponseResult) request.getAttribute(RESPONSE_RESULT);
        return responseResult == null ? false : true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        log.info("进入 返回体 重写格式 处理中 ......");

        if (body instanceof Exception) {
            log.info("返回值 异常 作包装 处理中 ......");

        }

        return Result.success(body);
    }
}
