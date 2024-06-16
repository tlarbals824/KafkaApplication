package com.sim.kafkaapplication.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.listener.ContainerProperties
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class JsonKafkaConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.kafka")
    fun kafkaProperties(): KafkaProperties {
        return KafkaProperties()
    }

    @Bean
    fun consumerFactory(kafkaProperties: KafkaProperties): ConsumerFactory<String, String> {
        val props = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            JsonDeserializer.TRUSTED_PACKAGES to "*",
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "latest",
            ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG to "false",
            ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG to "false",
        )
        return DefaultKafkaConsumerFactory(
            props,
            StringDeserializer(),
            StringDeserializer()
        )
    }

    @Bean
    fun kafkaListenerContainerFactory(
        consumerFactory: ConsumerFactory<String, String>
    ): ConcurrentKafkaListenerContainerFactory<String, String> {
        return ConcurrentKafkaListenerContainerFactory<String, String>().apply {
            setConsumerFactory(consumerFactory)
            containerProperties.ackMode = ContainerProperties.AckMode.MANUAL
        }
    }

    @Bean
    fun producerFactory(kafkaProperties: KafkaProperties): ProducerFactory<String, Any>{
        val props = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to kafkaProperties.producer.keySerializer,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to kafkaProperties.producer.valueSerializer,
            ProducerConfig.ACKS_CONFIG to kafkaProperties.producer.acks,
            ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG to "true"
        )
        return DefaultKafkaProducerFactory(props)
    }

    @Bean("kafkaTemplate")
    fun kafkaTemplate(
        kafkaProperties: KafkaProperties
    ): KafkaTemplate<String, *>{
        return KafkaTemplate(producerFactory(kafkaProperties))
    }

}