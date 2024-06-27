package com.sim.metadataclient.metadata

import com.sim.core.MetadataPort
import org.springframework.stereotype.Component

@Component
class MetadataAdapter(
    private val metadataClient: MetadataClient
) : MetadataPort {
    override fun getCategoryNameByCategoryId(categoryId: String): String {
        return metadataClient.getCategoryById(categoryId).name
    }

    override fun getUserNameByUserId(userId: String): String {
        return metadataClient.getUserById(userId).name
    }

    override fun listFollowerIdsByUserId(userId: String): List<String> {
        return metadataClient.getFollowerIdsByUserId(userId)
    }
}