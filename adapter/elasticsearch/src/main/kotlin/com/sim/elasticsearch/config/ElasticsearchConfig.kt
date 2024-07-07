package com.sim.elasticsearch.config

import org.apache.http.HttpHeaders
import org.apache.http.HttpHost
import org.apache.http.HttpResponseInterceptor
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.message.BasicHeader
import org.elasticsearch.client.RestClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import org.springframework.http.MediaType

@Configuration
@EnableElasticsearchRepositories(basePackages = ["com.sim.elasticsearch"])
class ElasticsearchConfig {

    @Bean
    fun getRestClient(): RestClient {
        val credentialsProvider = BasicCredentialsProvider()
        return RestClient.builder(HttpHost("localhost", 9200, "http"))
            .setHttpClientConfigCallback {
                it.disableAuthCaching()
                it.setDefaultHeaders(
                    listOf(BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                )
                it.addInterceptorLast(HttpResponseInterceptor { res, _ ->
                    res.addHeader("X-Elastic-Product", "Elasticsearch")
                })
                it.setDefaultCredentialsProvider(credentialsProvider)
            }.build()
    }
}

