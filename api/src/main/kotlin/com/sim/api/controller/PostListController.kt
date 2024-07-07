package com.sim.api.controller

import com.sim.api.model.PostListResponse
import com.sim.postsearchusecase.PostSearchUsecase
import com.sim.subscribingpostusecase.SubscribingPostListUsecase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostListController(
    private val subscribingPostListUsecase: SubscribingPostListUsecase,
    private val postSearchUsecase: PostSearchUsecase
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
        @RequestParam("query") query: String,
        @RequestParam("pageNumber") pageNumber: Int
    ): List<PostListResponse> {
        return postSearchUsecase.searchPostsByKeyword(
            query,
            pageNumber
        ).map {
            PostListResponse(
                it.postId,
                it.title,
                it.username,
                it.createdAt
            )
        }
    }
}