package com.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.study.interceptor.LoggerInterceptor;



//WebMvcConfigurer는 @EnableWebMvc의 자동 설정을 베이스로 함
@Configuration
public class WebMvmConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//인터셉터 레지스트리에 인터셉터 하나 등록.인터셉터에서 제외할 경로의 패턴 등록
		 registry.addInterceptor(new LoggerInterceptor())
         .excludePathPatterns("/css/**", "/fonts/**", "/plugin/**", "/scripts/**");
		
	}
	
	
}
