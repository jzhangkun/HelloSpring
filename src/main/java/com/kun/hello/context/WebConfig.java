package com.kun.hello.context;


import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;


/**
 * Created by jzhangkun on 23/06/2017.
 */

@Configuration
@EnableWebMvc
@ComponentScan({ "com.kun.hello" })
@PropertySource("classpath:${environment}.properties")
@Import(JpaConfiguration.class)
//@Profile("dev")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ExecuteTimeInterceptor()).addPathPatterns("/**");
    }

}
