package com.sim.api.controller

import com.sim.api.model.PostListResponse
import com.sim.postresolvinghelpusecase.PostResolvingHelpUsecase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostListController(
    private val postResolvingHelpUsecase: PostResolvingHelpUsecase
) {

    @GetMapping("/inbox/{userId}")
    fun listSubscribingPosts(
        @PathVariable userId: String
    ): List<PostListResponse> {
        return listOf()
    }

    @GetMapping("/search")
    fun searchPosts(
        @RequestParam("query") query: String
    ):List<PostListResponse> {
        return listOf()
    }
}