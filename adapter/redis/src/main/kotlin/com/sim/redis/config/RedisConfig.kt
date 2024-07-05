package com.sim.redis.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class RedisConfig {


    @Bean
    @ConfigurationProperties("spring.redis")
    fun redisProperties(): RedisProperties {
        return RedisProperties()
    }

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        val redisProperties = redisProperties()
        return LettuceConnectionFactory(
            RedisStandaloneConfiguration(redisProperties.host, redisProperties.port)
        )
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, *> {
        val redisTemplate = RedisTemplate<String, Any>()
        redisTemplate.connectionFactory = redisConnectionFactory()
        return redisTemplate
    }
}

data class RedisProperties(
    var host: String = "localhost",
    var port: Int = 6379,
)