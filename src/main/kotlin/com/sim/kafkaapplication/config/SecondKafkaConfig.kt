package com.sim.kafkaapplication.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Primary
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.web.bind.annotation.PostMapping

@Configuration
class SecondKafkaConfig {

    @Bean
    @Qualifier("secondKafkaProperties")
    @ConfigurationProperties(prefix = "spring.kafka.string")
    fun secondKafkaProperties(): KafkaProperties {
        return KafkaProperties()
    }

    @Bean
    @Qualifier("secondConsumerFactory")
    fun secondConsumerFactory(secondKafkaProperties: KafkaProperties): ConsumerFactory<String, Any> {
        val props = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to secondKafkaProperties.bootstrapServers,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to secondKafkaProperties.consumer.keyDeserializer,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to secondKafkaProperties.consumer.valueDeserializer,
            JsonDeserializer.TRUSTED_PACKAGES to "*",
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "latest",
            ConsumerConfig.ALLOW_AUTO_CREATE_TOPICS_CONFIG to "false"
        )
        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    @Qualifier("secondKafkaListenerContainerFactory")
    fun secondKafkaListenerContainerFactory(
        secondConsumerFactory: ConsumerFactory<String, Any>
    ): ConcurrentKafkaListenerContainerFactory<String, Any> {
        return ConcurrentKafkaListenerContainerFactory<String, Any>().apply {
            setConsumerFactory(secondConsumerFactory)
            setConcurrency(1)
        }
    }

    @Bean
    @Qualifier("secondProducerFactory")
    fun secondProducerFactory(secondKafkaProperties: KafkaProperties): ProducerFactory<String, Any>{
        val props = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to secondKafkaProperties.bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to secondKafkaProperties.producer.keySerializer,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to secondKafkaProperties.producer.valueSerializer,
            ProducerConfig.ACKS_CONFIG to secondKafkaProperties.producer.acks
        )
        return DefaultKafkaProducerFactory(props)
    }

    @Bean
    @Qualifier("secondKafkaTemplate")
    fun secondKafkaTemplate(
        secondKafkaProperties: KafkaProperties
    ): KafkaTemplate<String, *>{
        return KafkaTemplate(secondProducerFactory(secondKafkaProperties))
    }

}