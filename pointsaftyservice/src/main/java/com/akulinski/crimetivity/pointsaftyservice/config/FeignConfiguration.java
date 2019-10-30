package com.akulinski.crimetivity.pointsaftyservice.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public Logger.Level configureLogLevel(){
        return  Logger.Level.FULL;
    }


    @Bean
    public Request.Options timeoutConfiguration(){
        return new Request.Options(5000, 30000);
    }

}
