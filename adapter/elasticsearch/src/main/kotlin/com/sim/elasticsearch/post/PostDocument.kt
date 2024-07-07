package com.sim.elasticsearch.post

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDateTime

@Document(indexName = "post-1") // es에는 alias가 있음, alias를 사용하면 indexName을 변경해도 alias를 통해 접근 가능
class PostDocument(
    id: String,
    title: String,
    content: String,
    categoryName: String,
    tags: List<String>,
    indexedAt: LocalDateTime = LocalDateTime.now()
) {
    @Id
    val id: String = id
    val title: String = title
    val content: String = content
    val categoryName: String = categoryName
    val tags: List<String> = tags
    @Field(type = FieldType.Date, format = [DateFormat.date_hour_minute_second_millis])
    val indexedAt: LocalDateTime = indexedAt
}