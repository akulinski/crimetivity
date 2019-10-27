package com.akulinski.crimetivity.citylocationservice.config

import com.akulinski.crimetivity.citylocationservice.core.domain.GoogleApiResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer


@Configuration
class RedisConfig(@Value("\${spring.redis.host}") val host: String, @Value("\${spring.redis.port}") val port: Int) {

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(host, port)
    }

    @Bean
    fun redisTemplateString(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String, GoogleApiResponse> {

        val redisTemplate = RedisTemplate<String, GoogleApiResponse>()

        redisTemplate.setConnectionFactory(redisConnectionFactory)

        redisTemplate.setDefaultSerializer(GenericJackson2JsonRedisSerializer())
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.hashKeySerializer = GenericJackson2JsonRedisSerializer()
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer()

        return redisTemplate
    }
}