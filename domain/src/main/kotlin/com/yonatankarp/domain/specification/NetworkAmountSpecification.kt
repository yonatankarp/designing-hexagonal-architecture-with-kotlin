package com.yonatankarp.domain.specification

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.specification.shared.AbstractSpecification

class NetworkAmountSpecification : AbstractSpecification<Router>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun isSatisfiedBy(router: Router) = router.networkSwitch.networks.size <= MAXIMUM_ALLOWED_NETWORKS

    companion object {
        private const val MAXIMUM_ALLOWED_NETWORKS = 6
    }
}
