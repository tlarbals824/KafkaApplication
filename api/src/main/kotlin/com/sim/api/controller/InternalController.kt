package com.sim.api.controller

import com.sim.inspectedpostusecase.PostInspectUsecase
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/internal")
class InternalController(
    private val postInspectUsecase: PostInspectUsecase
) {


}