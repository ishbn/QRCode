package com.doze.freeze.qrcode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @BelongsProject: qrcode
 * @BelongsPackage: com.doze.freeze.qrcode.config
 * @Author: hbn
 * @CreateTime: 2021-07-20 23:44
 * @Description:
 */
@Configuration
public class QrCodeConfig extends WebMvcConfigurationSupport {
    @Bean
    public BufferedImageHttpMessageConverter addConverter(){
        return new BufferedImageHttpMessageConverter();
    }

    @Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new BufferedImageHttpMessageConverter());
    }

    //这里配置静态资源文件的路径导包都是默认的直接导入就可以
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        //这个配置，访问时的路径需要加static
        // registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }
}
