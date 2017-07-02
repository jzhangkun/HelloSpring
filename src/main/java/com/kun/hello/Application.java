package com.kun.hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;


/**
 * Created by jzhangkun on 23/06/2017.
 */

@Configuration
@EnableWebMvc
@ComponentScan
@PropertySource("classpath:application.properties")
@Profile("dev")
public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
        ConfigurableEnvironment env = ctx.getEnvironment();
        ctx.getEnvironment().setActiveProfiles("development");
        ctx.getEnvironment().setActiveProfiles();
        String runEnv = env.getProperty("environment");
        System.out.println("running environment: " + runEnv);
    }
}
