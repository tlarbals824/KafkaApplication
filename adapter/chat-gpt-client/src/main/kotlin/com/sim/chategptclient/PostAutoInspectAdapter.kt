package com.sim.chategptclient

import com.fasterxml.jackson.databind.ObjectMapper
import com.sim.core.PostAutoInspectPort
import com.sim.domain.inspectedpost.AutoInspectionResult
import com.sim.domain.post.Post
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {  }

@Component
class PostAutoInspectAdapter(
    private val chatGptClient: ChatGptClient,
    private val objectMapper: ObjectMapper
) : PostAutoInspectPort {
    override fun inspect(
        post: Post,
        categoryName: String
    ): AutoInspectionResult {
        val content = buildContentString(post, categoryName)
        val policy = ChatGptClient.Policy(
            AutoInspectionPolicy.INSPECTION_INSTRUCTION,
            AutoInspectionPolicy.EXAMPLE_CONTENT,
            AutoInspectionPolicy.EXAMPLE_INSPECTION_RESULT,
            true
        )
        val result = chatGptClient.getResultForContentWithPolicy(content, policy)
        logger.info { result }
        return objectMapper.readValue(result, AutoInspectionResult::class.java)
    }

    private fun buildContentString(post: Post, categoryName: String): String {
        return "[$categoryName] ${post.title} - ${post.content}"
    }

    private object AutoInspectionPolicy {
        const val INSPECTION_INSTRUCTION = """
            The task you need to accomplish is to return two items ('status' and 'tags') in JSON format.
            The information I will provide will be in the format '[Post category] Post content.
            Then, if the content of the post aligns well with the meaning or theme of the post category, 
            fill the 'status' field with the string 'GOOD.'
            If the meaning or theme appears unrelated,
            fill the 'status' field with the string 'BAD.' 
            Additionally, extract and compile a list of up to 5 keywords 
            that seem most important in the post content and populate the 'tags' field with them.
        """

        const val EXAMPLE_CONTENT = """
            [Health] Reps and Muscle Size - To increase muscle size,
            it is considered most ideal to exercise with the maximum weight
            that allows 8 to 12 repetitions per set.
        """

        const val EXAMPLE_INSPECTION_RESULT = """
            {
                "status" : "GOOD",
                "tags" : ["muscle","weight","repetitions"]
            }
        """
    }

}