package com.sim.mysql.coupon

import com.sim.core.CouponEventPort
import com.sim.domain.coupon.CouponEvent
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class CouponEventAdapter(
    private val couponEventJpaRepository: CouponEventJpaRepository
) : CouponEventPort {
    override fun findByIdOrNull(id: String): CouponEvent? {
        return couponEventJpaRepository.findByIdOrNull(id)?.let {
            CouponEntityConverter.toModel(it)
        }
    }
}