package com.yonatankarp.application.ports.output

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.valueobject.RouterId

interface RouterNetworkOutputPort {
    fun fetchRouterById(routerId: RouterId): Router?

    fun persistRouter(router: Router): Boolean
}
