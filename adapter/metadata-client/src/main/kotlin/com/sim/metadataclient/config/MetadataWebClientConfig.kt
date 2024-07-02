package com.sim.metadataclient.config

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

private val logger = KotlinLogging.logger {  }

@Configuration
class MetadataWebClientConfig(
    @Value("\${external-server.metadata.url}")
    private val metadataApiUrl: String
) {

    @Bean(name = ["metadataWebClient"])
    fun metadataWebClient(): WebClient{
        return WebClient.builder()
            .baseUrl("http://$metadataApiUrl")
            .build()
    }

}