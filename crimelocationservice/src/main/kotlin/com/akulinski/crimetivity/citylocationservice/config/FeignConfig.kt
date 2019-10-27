package com.akulinski.crimetivity.citylocationservice.config

import com.akulinski.crimetivity.citylocationservice.core.GeoClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import feign.slf4j.Slf4jLogger
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import feign.Feign
import feign.Logger
import feign.okhttp.OkHttpClient
import org.springframework.beans.factory.annotation.Value


@Configuration
class FeignConfig(@Value("\${google.maps.url}") val url: String) {

    @Bean
    fun geoClient(): GeoClient{
       return Feign.builder()
                .client(OkHttpClient())
                .encoder(GsonEncoder())
                .decoder(GsonDecoder())
                .logger(Slf4jLogger(GeoClient::class.java))
                .logLevel(Logger.Level.FULL)
                .target(GeoClient::class.java, url)
    }
}