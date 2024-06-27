package com.sim.metadataclient.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class MetadataWebClientConfig(
    @Value("\${external-server.metadata.url}")
    private val metadataApiUrl: String
) {

    @Bean
    fun metadataWebClient(): WebClient{
        return WebClient.builder()
            .baseUrl(metadataApiUrl)
            .build()
    }

}