package com.sim.api.controller

import com.sim.api.model.PostListResponse
import com.sim.subscribingpostusecase.SubscribingPostListUsecase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostListController(
    private val subscribingPostListUsecase: SubscribingPostListUsecase
) {

    @GetMapping("/inbox/{userId}")
    fun listSubscribingPosts(
        @PathVariable userId: String,
        @RequestParam pageNumber: Int
    ): List<PostListResponse> {
        return subscribingPostListUsecase.listSubscribingInboxPosts(
            SubscribingPostListUsecase.Request(
                pageNumber,
                userId
            )
        ).map {
            PostListResponse(
                it.postId,
                it.title,
                it.username,
                it.createdAt
            )
        }
    }

    @GetMapping("/search")
    fun searchPosts(
        @RequestParam("query") query: String
    ): List<PostListResponse> {
        return listOf()
    }
}