package com.sim.mysql.coupon;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponEventJpaRepository extends JpaRepository<CouponEventEntity, String> {
}
