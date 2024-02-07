package com.yonatankarp.domain.service

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.specification.CIDRSpecification
import com.yonatankarp.domain.specification.NetworkAmountSpecification
import com.yonatankarp.domain.specification.NetworkAvailabilitySpecification
import com.yonatankarp.domain.specification.RouterTypeSpecification
import com.yonatankarp.domain.valueobject.Network

object NetworkOperation {
    fun createNetWork(
        router: Router,
        network: Network,
    ): Router? {
        val availabilitySpec =
            NetworkAvailabilitySpecification(
                network.address,
                network.name,
                network.cidr,
            )
        val cidrSpec = CIDRSpecification()
        val networkSpec =
            RouterTypeSpecification() and NetworkAmountSpecification()

        require(cidrSpec.isSatisfiedBy(network.cidr)) { "CIDR is below ${CIDRSpecification.MINIMUM_ALLOWED_CIDR}" }
        require(availabilitySpec.isSatisfiedBy(router)) { "Address already exist" }

        return if (networkSpec.isSatisfiedBy(router)) {
            val newNetwork =
                router.createNetwork(
                    network.address,
                    network.name,
                    network.cidr,
                )
            router.addNetworkToSwitch(newNetwork)
        } else {
            null
        }
    }
}
