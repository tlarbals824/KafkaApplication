package com.sim.core

interface MetadataPort {

    fun getCategoryNameByCategoryId(categoryId: String): String
    fun getUserNameByUserId(userId: String): String
    fun listFollowerIdsByUserId(userId: String): List<String>
}