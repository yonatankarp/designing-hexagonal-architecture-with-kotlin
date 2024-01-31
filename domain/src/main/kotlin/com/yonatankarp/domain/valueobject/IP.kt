package com.yonatankarp.domain.valueobject

data class IP(val address: String) {
    val protocol =
        if (address.length <= 15) {
            Protocol.IPV4
        } else {
            Protocol.IPV6
        }
}
