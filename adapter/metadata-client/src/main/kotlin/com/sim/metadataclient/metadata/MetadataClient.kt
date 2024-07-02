package com.sim.metadataclient.metadata

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

private val logger = KotlinLogging.logger {  }

@Component
class MetadataClient(
    private val metadataWebClient: WebClient
) {

    fun getCategoryById(id: String): CategoryResponse {
        return metadataWebClient.get()
            .uri("/categories/${id}")
            .retrieve()
            .bodyToMono(CategoryResponse::class.java)
            .block() ?: throw RuntimeException("category not found")
    }

    fun getUserById(id: String): UserResponse {
        return metadataWebClient.get()
            .uri("/users/${id}")
            .retrieve()
            .bodyToMono(UserResponse::class.java)
            .block() ?: throw RuntimeException("user not found")
    }

    fun getFollowerIdsByUserId(userId: String): List<String> {
        return metadataWebClient.get()
            .uri("/followers?followingId=${userId}")
            .retrieve()
            .bodyToFlux(Int::class.java)
            .collectList()
            .block()?.map { it.toString() } ?: emptyList()
    }

    data class CategoryResponse(
        val id: String,
        val name: String
    )

    data class UserResponse(
        val id: String,
        val name: String,
        val email: String
    )
}