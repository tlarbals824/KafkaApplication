package com.sim.api.controller

import com.sim.domain.inspectedpost.InspectedPost
import com.sim.domain.post.Post
import com.sim.inspectedpostusecase.PostInspectUsecase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/internal")
class InternalController(
    private val postInspectUsecase: PostInspectUsecase
) {

    @GetMapping
    fun inspect(
        @RequestParam("title") title: String,
        @RequestParam("content") content: String,
        @RequestParam("categoryId") categoryId: String,
    ): InspectedPost?{
        return postInspectUsecase.inspectAndGetIfValid(
            Post(
                title=title,
                content = content,
                userId = " ",
                categoryId = categoryId
            )
        )
    }
}