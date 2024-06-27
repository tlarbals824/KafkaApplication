package com.sim.postusecase

interface PostDeleteUsecase {

    fun delete(command: Command): Unit

    data class Command(
        val postId: String
    )
}