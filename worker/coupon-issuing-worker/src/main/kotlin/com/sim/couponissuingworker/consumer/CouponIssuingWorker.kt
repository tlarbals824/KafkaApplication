package com.sim.couponissuingworker.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import com.sim.couponusecase.IssueCouponUsecase
import com.sim.kafka.adapter.coupon.CouponIssueRequestMessage
import com.sim.kafka.common.Topic
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CouponIssuingWorker(
    private val issueCouponUsecase: IssueCouponUsecase,
    private val objectMapper: ObjectMapper
) {

    @KafkaListener(
        topics = [Topic.COUPON_ISSUE_REQUEST],
        groupId = "coupon-issuing-consumer-group"
    )
    fun consume(message: ConsumerRecord<String, String>){
        val couponMessage = objectMapper.readValue(message.value(), CouponIssueRequestMessage::class.java)
        issueCouponUsecase.save(couponMessage.couponEventId, couponMessage.userId)
    }
}