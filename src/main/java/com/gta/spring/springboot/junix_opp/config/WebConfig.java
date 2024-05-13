package com.gta.spring.springboot.junix_opp.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")//http://example.com") // замените на ваш домен
                .allowedMethods("GET", "POST", "PUT", "DELETE") // разрешенные HTTP методы
                .allowedHeaders("*"); // разрешенные заголовки
    }
}
