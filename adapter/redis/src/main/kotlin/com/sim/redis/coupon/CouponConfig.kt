package com.sim.redis.coupon

import com.fasterxml.jackson.databind.ObjectMapper
import com.sim.core.CouponEventPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class CouponConfig(
    private val couponEventPort: CouponEventPort,
    private val redisTemplate: RedisTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    @Bean
    @Primary
    fun couponEventPort() : CouponEventPort{
        return CouponEventCacheProxy(
            couponEventPort,
            redisTemplate,
            objectMapper
        )
    }

}