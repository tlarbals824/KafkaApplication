package com.sim.elasticsearch.post

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

interface PostSearchRepository: ElasticsearchRepository<PostDocument, String> {

}