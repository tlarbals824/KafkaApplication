package com.sim.kafkaapplication.consumer

import com.sim.kafkaapplication.model.MyMessage
import org.springframework.messaging.Message
import org.springframework.stereotype.Component
import java.util.function.Function

//@Component
class CloudStreamMyConsumer : Function<Message<MyMessage>, Unit>{

    override fun apply(message: Message<MyMessage>) {
        println("Received message: ${message.payload}")
    }
}