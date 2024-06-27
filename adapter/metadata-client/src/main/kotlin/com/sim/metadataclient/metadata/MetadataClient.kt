package com.sim.metadataclient.metadata

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class MetadataClient(
    private val webClient: WebClient
){

    fun getCategoryById(id: String): CategoryResponse{
        return webClient.get()
            .uri("/categories/${id}")
            .retrieve()
            .bodyToMono(CategoryResponse::class.java)
            .block() ?: throw RuntimeException("category not found")
    }

    fun getUserById(id: String): UserResponse{
        return webClient.get()
            .uri("/user/${id}")
            .retrieve()
            .bodyToMono(UserResponse::class.java)
            .block() ?: throw RuntimeException("user not found")
    }

    fun getFollowerIdsByUserId(userId: String): List<String>{
        return webClient.get()
            .uri("/followers?followingId=${userId}")
            .retrieve()
            .bodyToFlux(String::class.java)
            .collectList()
            .block() ?: emptyList()
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