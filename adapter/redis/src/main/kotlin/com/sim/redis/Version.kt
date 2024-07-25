package com.sim.redis

object Version {

    object ResolvedPost{
        const val V1 = "resolved_post:v1"
    }

    object Coupon{
        const val USER_REQUEST_HISTORY_KEY_PREFIX = "coupon_history.user_request.v1"
        const val REQUEST_COUNT_HISTORY_KEY_PREFIX = "coupon_history.request_count.v1"

        const val COUPON_EVENT_CACHE_KEY = "coupon_event:v1"
    }
}