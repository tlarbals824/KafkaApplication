package com.sim.chategptclient.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class ChatGptWebClientConfig(
) {
    @Bean(name = ["chatGptWebClient"])
    fun chatGptWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://api.openai.com")
            .build()
    }
}