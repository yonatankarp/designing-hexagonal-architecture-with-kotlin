package com.yonatankarp.domain.valueobject

import kotlin.random.Random
import kotlin.random.nextUInt

object NetworkFixture {
    val validNetworkIPv4: Network get() = Network(IPFixture.validIPv4, "Network1", 12u)
    val validNetworkIPv6: Network get() = Network(IPFixture.validIPv6, "Network2", 32u)

    val multipleNetworks: List<Network> get() =
        listOf(
            randomValidNetworkIpv4,
            randomValidNetworkIpv4,
            randomValidNetworkIpv4,
        )

    val randomValidNetworkIpv4: Network get() = Network(IPFixture.randomIPv4, "RandomNetwork", Random.nextUInt(1u, 33u))
    val randomValidNetworkIpv6: Network get() = Network(IPFixture.randomIPv6, "RandomNetwork", Random.nextUInt(1u, 33u))
}
