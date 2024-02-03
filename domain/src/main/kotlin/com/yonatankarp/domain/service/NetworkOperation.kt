package com.yonatankarp.domain.service

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.specification.CIDRSpecification
import com.yonatankarp.domain.specification.NetworkAmountSpecification
import com.yonatankarp.domain.specification.NetworkAvailabilitySpecification
import com.yonatankarp.domain.specification.RouterTypeSpecification
import com.yonatankarp.domain.valueobject.IP

class NetworkOperation {
    fun createNetWork(
        router: Router,
        address: IP,
        name: String,
        cidr: UInt,
    ) {
        val availabilitySpec = NetworkAvailabilitySpecification(address, name, cidr)
        val cidrSpec = CIDRSpecification()
        val networkSpec = RouterTypeSpecification() and NetworkAmountSpecification()

        require(cidrSpec.isSatisfiedBy(cidr)) { "CIDR is below ${CIDRSpecification.MINIMUM_ALLOWED_CIDR}" }
        require(availabilitySpec.isSatisfiedBy(router)) { "Address already exist" }

        if (networkSpec.isSatisfiedBy(router)) {
            val network = router.createNetwork(address, name, cidr)
            router.addNetworkToSwitch(network)
        }
    }
}
