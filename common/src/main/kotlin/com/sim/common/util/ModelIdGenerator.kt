package com.sim.common.util

import java.util.*

object ModelIdGenerator {

    fun generateId(): String {
        return UUID.randomUUID().toString()
    }
}