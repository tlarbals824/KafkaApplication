package com.sim.elasticsearch.post

import com.sim.core.PostSearchPort
import com.sim.domain.inspectedpost.InspectedPost
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.query.Criteria
import org.springframework.data.elasticsearch.core.query.CriteriaQuery
import org.springframework.data.elasticsearch.core.query.Query
import org.springframework.stereotype.Component

@Component
class PostSearchAdapter(
    private val postSearchRepository: PostSearchRepository,
    private val elasticsearchOperations: ElasticsearchOperations
) : PostSearchPort {
    override fun indexPost(post: InspectedPost) {
        postSearchRepository.save(PostDocumentConverter.toDocument(post))
    }

    override fun deletePostById(id: String) {
        postSearchRepository.deleteById(id)
    }

    override fun searchPostIdsByKeyword(keyword: String, pageNumber: Int, pageSize: Int): List<String> {
        // keywork is non-blank
        assert(keyword.isNotBlank())
        val query = Criteria("title").contains(keyword)
            .or("content").contains(keyword)
            .or("tags").`is`(keyword)
            .or("categoryName").`is`(keyword)

        val searchHits = elasticsearchOperations.search(
            CriteriaQuery(query).setPageable(PageRequest.of(pageNumber, pageSize)) as Query,
            PostDocument::class.java
        )
        return searchHits.searchHits.map {
            it.content.id
        }
    }
}