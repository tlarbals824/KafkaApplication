package com.sim.kafkaapplication.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class KafkaBatchKafkaConfig {



    @Bean
    @Qualifier("batchConsumerFactory")
    fun batchConsumerFactory(kafkaProperties: KafkaProperties): ConsumerFactory<String, Any> {
        val props = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to kafkaProperties.consumer.keyDeserializer,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to kafkaProperties.consumer.valueDeserializer,
            JsonDeserializer.TRUSTED_PACKAGES to "*",
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "latest",
            ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG to "false",
            ConsumerConfig.MAX_POLL_RECORDS_CONFIG to ConsumerConfig.DEFAULT_MAX_POLL_RECORDS
        )
        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    @Qualifier("batchKafkaListenerContainerFactory")
    fun batchKafkaListenerContainerFactory(
        batchConsumerFactory: ConsumerFactory<String, Any>
    ): ConcurrentKafkaListenerContainerFactory<String, Any> {
        return ConcurrentKafkaListenerContainerFactory<String, Any>().apply {
            setConsumerFactory(batchConsumerFactory)
            setConcurrency(1)
            isBatchListener = true
            containerProperties.ackMode = ContainerProperties.AckMode.BATCH
        }
    }
}