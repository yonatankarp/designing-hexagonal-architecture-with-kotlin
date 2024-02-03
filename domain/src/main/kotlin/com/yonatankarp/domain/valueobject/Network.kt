package com.yonatankarp.domain.valueobject

data class Network(
    val address: IP,
    val name: String,
    val cidr: UInt,
) {
    init {
        require(cidr in 1u..32u) { "Invalid CIDR value" }
    }
}
