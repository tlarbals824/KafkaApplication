package com.sim.mongodb.subscribepost

import com.sim.mongodb.common.Collections
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

interface SubscribingPostRepository : MongoRepository<SubscribingPostDocument, String>, SubscribingPostCustomRepository{
}

interface SubscribingPostCustomRepository{
    fun findByFollowerUserIdWithPagination(followerId: String, pageNumber: Int, size: Int): List<SubscribingPostDocument>

    fun deleteAllByPostId(postId: String)
}


@Repository
class SubscribingPostCustomRepositoryImpl(
    private val mongoTemplate: MongoTemplate
) : SubscribingPostCustomRepository {
    override fun findByFollowerUserIdWithPagination(followerId: String, pageNumber: Int, size: Int): List<SubscribingPostDocument> {
        val query = Query()
            .addCriteria(Criteria.where("followerUserId").`is`(followerId))
            .with(PageRequest.of(pageNumber, size, Sort.by(Sort.Direction.DESC, "postCreatedAt")))

        return mongoTemplate.find(query, SubscribingPostDocument::class.java, Collections.SUBSCRIBING_POST)
    }

    override fun deleteAllByPostId(postId: String) {
        val query = Query()
            .addCriteria(Criteria.where("postId").`is`(postId))
        mongoTemplate.remove(query, SubscribingPostDocument::class.java)
    }
}
