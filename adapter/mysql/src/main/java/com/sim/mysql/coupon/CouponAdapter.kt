package com.sim.mysql.coupon

import com.sim.core.CouponPort
import com.sim.domain.coupon.Coupon
import com.sim.domain.coupon.ResolvedCoupon
import org.springframework.stereotype.Component

@Component
class CouponAdapter(
    private val couponRepository: CouponJpaRepository
) : CouponPort {
    override fun save(coupon: Coupon): Coupon {
        return CouponEntityConverter.toModel(couponRepository.save(CouponEntityConverter.toEntity(coupon)))
    }

    override fun listByUserId(userId: String): List<ResolvedCoupon> {
        return couponRepository.findAllByUserId(userId).map { CouponEntityConverter.toResolveCoupon(it) }
    }
}