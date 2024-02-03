package com.yonatankarp.domain.valueobject

import kotlin.random.Random

object IPFixture {
    val validIPv4: IP get() = IP("192.168.1.1")
    val validIPv6: IP get() = IP("2001:0db8:85a3:0000:0000:8a2e:0370:7334")

    val emptyIPv4: IP get() = IP("")
    val invalidIPv6: IP get() = IP("invalid")

    val randomIPv4: IP get() = IP(generateRandomIPv4())
    val randomIPv6: IP get() = IP(generateRandomIPv6())

    private fun generateRandomIPv4(): String {
        val ipAddress = StringBuilder()

        repeat(4) {
            ipAddress.append(Random.nextInt(256))
            ipAddress.append(".")
        }

        return ipAddress.removeSuffix(".").toString()
    }

    private fun generateRandomIPv6(): String {
        val ipAddress = StringBuilder()

        repeat(8) {
            ipAddress.append(String.format("%04x", Random.nextInt(65536)))
            ipAddress.append(":")
        }

        return ipAddress.removeSuffix(":").toString()
    }
}
