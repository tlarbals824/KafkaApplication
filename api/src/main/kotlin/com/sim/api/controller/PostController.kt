package com.sim.api.controller

import com.sim.api.model.PostCreateRequest
import com.sim.api.model.PostDetailResponse
import com.sim.api.model.PostResponse
import com.sim.api.model.PostUpdateRequest
import com.sim.postusecase.PostCreateUsecase
import com.sim.postusecase.PostDeleteUsecase
import com.sim.postusecase.PostReadUseCase
import com.sim.postusecase.PostUpdateUsecase
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(
    private val postCreateUsecase: PostCreateUsecase,
    private val postUpdateUsecase: PostUpdateUsecase,
    private val postDeleteUsecase: PostDeleteUsecase,
    private val postReadUseCase: PostReadUseCase
) {


    @PostMapping
    fun createPost(
        @RequestBody request: PostCreateRequest
    ): PostResponse {
        val post = postCreateUsecase.create(request.toCommand())
        return PostResponse.from(post)
    }

    @PutMapping("/{postId}")
    fun updatePost(
        @PathVariable postId: String,
        @RequestBody request: PostUpdateRequest
    ) {
        postUpdateUsecase.update(request.toCommand(postId))
    }

    @DeleteMapping("/{postId}")
    fun deletePost(
        @PathVariable postId: String
    ) {
        postDeleteUsecase.delete(PostDeleteUsecase.Command(postId))
    }

    @GetMapping("/{postId}/detail")
    fun getPostDetail(
        @PathVariable postId: String
    ): PostDetailResponse {
        val post = postReadUseCase.read(postId)
        return PostDetailResponse.from(post)
    }

}