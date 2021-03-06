package org.andrey.springjpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages="org.andrey.springjpa")
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer{

	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}
	
	@Bean
	public JsonbHttpMessageConverter messageConverter() {
		return new JsonbHttpMessageConverter();
	}

}
