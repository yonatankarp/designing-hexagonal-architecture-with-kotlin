package com.yonatankarp.domain.specification

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.specification.shared.AbstractSpecification
import com.yonatankarp.domain.valueobject.RouterType

class RouterTypeSpecification : AbstractSpecification<Router?>() {
    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override fun isSatisfiedBy(router: Router?) =
        router != null &&
            (
                router.routerType == RouterType.EDGE ||
                    router.routerType == RouterType.CORE
            )
}
