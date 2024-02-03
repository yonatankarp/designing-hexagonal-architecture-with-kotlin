package com.yonatankarp.domain.specification

import com.yonatankarp.domain.specification.shared.AbstractSpecification

class CIDRSpecification : AbstractSpecification<UInt>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun isSatisfiedBy(cidr: UInt) = cidr > MINIMUM_ALLOWED_CIDR

    companion object {
        const val MINIMUM_ALLOWED_CIDR = 8u
    }
}
