package com.sim.mongodb.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["com.sim.mongodb"])
class MongoConfig(
    private val mongoTemplate: MongoTemplate
) {

}


