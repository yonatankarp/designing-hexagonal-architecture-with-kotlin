package com.yonatankarp.domain.entity

import com.yonatankarp.domain.valueobject.RouterId
import com.yonatankarp.domain.valueobject.RouterType

typealias RouterPredicate = (Router) -> Boolean

class Router(
    val routerType: RouterType,
    private val routerId: RouterId,
) {
    companion object {
        fun retrieveRouter(
            routers: List<Router>,
            predicate: RouterPredicate,
        ) = routers
            .filter(predicate)
            .toList()

        fun filterRouterByType(routerType: RouterType): RouterPredicate =
            when (routerType) {
                RouterType.CORE -> isCore()
                else -> isEdge()
            }

        private fun isCore(): RouterPredicate = { router -> router.routerType == RouterType.CORE }

        private fun isEdge(): RouterPredicate = { router -> router.routerType == RouterType.EDGE }
    }
}
