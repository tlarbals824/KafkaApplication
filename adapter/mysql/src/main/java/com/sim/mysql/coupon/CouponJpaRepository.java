package com.sim.mysql.coupon;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponJpaRepository extends JpaRepository<CouponEntity, String> {

    List<CouponEntity> findAllByUserId(String userId);
}
