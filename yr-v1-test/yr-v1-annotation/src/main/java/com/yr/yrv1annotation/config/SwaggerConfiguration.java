package com.yr.yrv1annotation.config;

import io.swagger.models.auth.In;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.lang.reflect.Field;
import java.util.*;

@EnableOpenApi
@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {

    @Value("${swagger.try-host}")
    private String tryHost;

    @Bean
    public Docket docket(Environment environment){
        // 设置显示的Swagger环境
        Profiles dev = Profiles.of("dev");
        // 获取项目环境
        boolean flag = environment.acceptsProfiles(dev);

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                // 配置Api文档分组
                .groupName("TIOXY")

                .enable(true) //是否启用Swagger，默认是true
                //.enable(flag) // 根据测试环境、生产环境来开关 swagger

                .select()
                // RequestHandlerSelectors,配置要扫描接口的方式
                // basePackage指定要扫描的包
                // any()扫描所有，项目中的所有接口都会被扫描到
                // none()不扫描
                // withClassAnnotation()扫描类上的注解
                // withMethodAnnotation()扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.yr.yrv1annotation.controller"))
                // paths()过滤某个路径
                .paths(PathSelectors.any())
                .build();

                // 支持的通讯协议集合
                //.protocols(newHashSet("https", "http"))
                // 授权信息设置，必要的header token等认证信息
                //.securitySchemes(securitySchemes())
                // 授权信息全局应用
                //.securityContexts(securityContexts());
    }

    /**
     * API 页面上半部分展示信息
     */
    private ApiInfo apiInfo(){
        // 作者信息
        Contact contact = new Contact("TIOXY", "https://www.cnblogs.com/tioxy/", "1369773052@qq.com");

        return new ApiInfo(
                "Tioxy的接口文档",
                "项目描述",
                "1.0",
                "https://www.cnblogs.com/tioxy/",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

    /**
     * 设置授权信息
     */
    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("BASE_TOKEN", "token", In.HEADER.toValue());
        return Collections.singletonList(apiKey);
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(Collections.singletonList(new SecurityReference("BASE_TOKEN", new AuthorizationScope[]{new AuthorizationScope("global", "")})))
                        .build()
        );
    }

    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }

    /**
     * 通用拦截器排除swagger设置，所有拦截器都会自动加swagger相关的资源排除信息
     */
    @SuppressWarnings("unchecked")
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        try {
            Field registrationsField = FieldUtils.getField(InterceptorRegistry.class, "registrations", true);
            List<InterceptorRegistration> registrations = (List<InterceptorRegistration>) ReflectionUtils.getField(registrationsField, registry);
            if (registrations != null) {
                for (InterceptorRegistration interceptorRegistration : registrations) {
                    interceptorRegistration
                            .excludePathPatterns("/swagger**/**")
                            .excludePathPatterns("/webjars/**")
                            .excludePathPatterns("/v3/**")
                            .excludePathPatterns("/doc.html");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
