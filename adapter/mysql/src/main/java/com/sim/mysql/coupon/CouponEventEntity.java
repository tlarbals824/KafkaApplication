package com.sim.mysql.coupon;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Table(name = "coupon_event")
@Entity
public class CouponEventEntity {
    @Id
    private String id;

    private String displayName;
    private LocalDateTime expiresAt;
    private Long issueLimit;

    protected CouponEventEntity() {
    }

    public CouponEventEntity(String id, String displayName, LocalDateTime expiresAt, Long issueLimit) {
        this.id = id;
        this.displayName = displayName;
        this.expiresAt = expiresAt;
        this.issueLimit = issueLimit;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public Long getIssueLimit() {
        return issueLimit;
    }
}
