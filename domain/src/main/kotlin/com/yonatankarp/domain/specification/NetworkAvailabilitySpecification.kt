package com.yonatankarp.domain.specification

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.specification.shared.AbstractSpecification
import com.yonatankarp.domain.valueobject.IP

class NetworkAvailabilitySpecification(
    private val address: IP,
    private val name: String,
    private val cidr: UInt,
) : AbstractSpecification<Router?>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun isSatisfiedBy(router: Router?) = isNetworkAvailable(router)

    private fun isNetworkAvailable(router: Router?) =
        router
            ?.networkSwitch
            ?.networks
            ?.none { it.address == address && it.name == name && it.cidr == cidr }
            ?: false
}
