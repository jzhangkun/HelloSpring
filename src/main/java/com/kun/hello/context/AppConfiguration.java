package com.kun.hello.context;


import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * Created by jzhangkun on 23/06/2017.
 */

@Configuration
@EnableWebMvc
@ComponentScan({ "com.kun.hello" })
@PropertySource("classpath:my.properties")
@Import(JpaConfiguration.class)
//@Profile("dev")
public class AppConfiguration {

}
