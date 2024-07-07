package com.sim.inspectedpostusecase

import com.sim.core.MetadataPort
import com.sim.core.PostAutoInspectPort
import com.sim.domain.inspectedpost.InspectedPost
import com.sim.domain.post.Post
import org.springframework.stereotype.Component

@Component
internal class PostInspectService(
    private val metadataPort: MetadataPort,
    private val autoInspectPort: PostAutoInspectPort
) : PostInspectUsecase {
    override fun inspectAndGetIfValid(post: Post): InspectedPost? {
        val categoryName = metadataPort.getCategoryNameByCategoryId(post.categoryId)
        val inspectionResult = autoInspectPort.inspect(post, categoryName)
        if(!inspectionResult.isGood()) return null
        return InspectedPost(
            post,
            categoryName,
            inspectionResult.tags.toList()
        )
    }
}