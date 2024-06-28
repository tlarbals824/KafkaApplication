package com.sim.core

import com.sim.domain.inspectedpost.AutoInspectionResult
import com.sim.domain.post.Post

interface PostAutoInspectPort {

    fun inspect(post: Post, categoryName: String): AutoInspectionResult
}