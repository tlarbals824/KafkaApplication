package com.sim.mongodb.subscribepost;

import com.sim.mongodb.common.Collections;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = Collections.SUBSCRIBING_POST)
public class SubscribingPostDocument {

    private String id;
    private String postId;
    private String followerUserId;
    private LocalDateTime postCreatedAt;
    private LocalDateTime addedAt;
    private Boolean read;

    public SubscribingPostDocument() {
    }

    public SubscribingPostDocument(String postId, String followerUserId, LocalDateTime postCreatedAt) {
        this.id = postId + "-"+followerUserId;
        this.postId = postId;
        this.followerUserId = followerUserId;
        this.postCreatedAt = postCreatedAt;
        this.addedAt = LocalDateTime.now();
        this.read = false;
    }

    public String getId() {
        return id;
    }

    public String getPostId() {
        return postId;
    }

    public String getFollowerUserId() {
        return followerUserId;
    }

    public LocalDateTime getPostCreatedAt() {
        return postCreatedAt;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public Boolean getRead() {
        return read;
    }
}
