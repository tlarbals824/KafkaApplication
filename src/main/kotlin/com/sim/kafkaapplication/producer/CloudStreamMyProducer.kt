//package com.sim.kafkaapplication.producer
//
//import com.sim.kafkaapplication.model.MyMessage
//import org.springframework.integration.support.MessageBuilder
//import org.springframework.kafka.support.KafkaHeaders
//import org.springframework.messaging.Message
//import org.springframework.stereotype.Component
//import reactor.core.publisher.Flux
//import reactor.core.publisher.Sinks
//import java.util.function.Supplier
//
//@Component
//class CloudStreamMyProducer : Supplier<Flux<Message<MyMessage>>> {
//    private val sinks = Sinks.many().unicast().onBackpressureBuffer<Message<MyMessage>>()
//
//    fun sendMessage(message: MyMessage) {
//        val requestMessage = MessageBuilder
//            .withPayload(message)
//            .setHeader(KafkaHeaders.KEY, message.age.toString())
//            .build()
//        sinks.emitNext(requestMessage, Sinks.EmitFailureHandler.FAIL_FAST)
//    }
//
//    override fun get(): Flux<Message<MyMessage>> {
//        return sinks.asFlux()
//    }
//}