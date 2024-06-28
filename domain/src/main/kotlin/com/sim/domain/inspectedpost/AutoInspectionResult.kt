package com.sim.domain.inspectedpost

data class AutoInspectionResult(
    val status: String, // GOOD or BAD,
    val tags: Array<String>
) {
}