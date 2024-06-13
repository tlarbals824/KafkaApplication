package com.sim.kafkaapplication.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.support.serializer.JsonDeserializer

@Configuration
class JsonKafkaConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.kafka.json")
    fun kafkaProperties(): KafkaProperties {
        return KafkaProperties()
    }

    @Bean
    fun consumerFactory(kafkaProperties: KafkaProperties): ConsumerFactory<String, Any> {
        val props = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to kafkaProperties.consumer.keyDeserializer,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to kafkaProperties.consumer.valueDeserializer,
            JsonDeserializer.TRUSTED_PACKAGES to "*",
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "latest",
            ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG to "false"
        )
        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun kafkaListenerContainerFactory(
        consumerFactory: ConsumerFactory<String, Any>
    ): ConcurrentKafkaListenerContainerFactory<String, Any> {
        return ConcurrentKafkaListenerContainerFactory<String, Any>().apply {
            setConsumerFactory(consumerFactory)
            setConcurrency(1)
        }
    }

    @Bean
    fun producerFactory(kafkaProperties: KafkaProperties): ProducerFactory<String, Any>{
        val props = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperties.bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to kafkaProperties.producer.keySerializer,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to kafkaProperties.producer.valueSerializer,
            ProducerConfig.ACKS_CONFIG to kafkaProperties.producer.acks
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