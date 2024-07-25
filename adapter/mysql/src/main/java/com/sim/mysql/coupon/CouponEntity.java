package com.sim.mysql.coupon;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(
        name = "coupon",
        uniqueConstraints = @UniqueConstraint(columnNames = {"couponEventId", "userId"})
)
@Entity
public class CouponEntity {
    @Id
    private String id;

    @Column(name = "coupon_event_id")
    private String couponEventId;
    private String userId;
    private LocalDateTime issuedAt;
    private LocalDateTime usedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_event_id", nullable = false, insertable = false, updatable = false)
    private CouponEventEntity couponEvent;

    protected CouponEntity() {
    }

    public CouponEntity(String id, String couponEventId, String userId, LocalDateTime issuedAt, LocalDateTime usedAt) {
        this.id = id;
        this.couponEventId = couponEventId;
        this.userId = userId;
        this.issuedAt = issuedAt;
        this.usedAt = usedAt;
    }

    public String getId() {
        return id;
    }

    public String getCouponEventId() {
        return couponEventId;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public LocalDateTime getUsedAt() {
        return usedAt;
    }

    public CouponEventEntity getCouponEvent() {
        return couponEvent;
    }
}
