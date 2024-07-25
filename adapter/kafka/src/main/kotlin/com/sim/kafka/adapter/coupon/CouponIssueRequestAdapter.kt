package com.sim.kafka.adapter.coupon

import com.fasterxml.jackson.databind.ObjectMapper
import com.sim.core.CouponIssueRequestPort
import com.sim.kafka.common.Topic
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class CouponIssueRequestAdapter(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper
) : CouponIssueRequestPort {
    override fun sendMessage(couponEventId: String, userId: String) {
        val couponIssueRequestMessage = CouponIssueRequestMessage(couponEventId, userId)
        val message = objectMapper.writeValueAsString(couponIssueRequestMessage)
        kafkaTemplate.send(Topic.COUPON_ISSUE_REQUEST, userId, message)
    }
}