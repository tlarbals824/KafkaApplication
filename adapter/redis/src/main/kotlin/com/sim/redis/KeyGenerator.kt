package com.sim.redis

object KeyGenerator {

    fun generateKey(version: String, key: String): String{
        return "$version:$key"
    }
}