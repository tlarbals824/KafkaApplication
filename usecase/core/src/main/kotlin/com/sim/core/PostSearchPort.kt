package com.sim.core

import com.sim.domain.inspectedpost.InspectedPost

interface PostSearchPort {

    fun indexPost(post: InspectedPost)

    fun deletePostById(id: String)

    fun searchPostIdsByKeyword(keyword: String, pageNumber: Int, pageSize: Int): List<String>
}