package com.yonatankarp.domain.entity

import com.yonatankarp.domain.valueobject.IP
import com.yonatankarp.domain.valueobject.Network
import com.yonatankarp.domain.valueobject.RouterId
import com.yonatankarp.domain.valueobject.RouterType

data class Router(
    val routerType: RouterType,
    val routerId: RouterId,
    var networkSwitch: Switch,
) {
    fun addNetworkToSwitch(network: Network) {
        networkSwitch = networkSwitch.addNetwork(network)
    }

    fun createNetwork(
        address: IP,
        name: String,
        cidr: UInt,
    ) = Network(address, name, cidr)

    companion object {
        fun filterRouterByType(routerType: RouterType): (Router) -> Boolean =
            when (routerType) {
                RouterType.CORE -> isCore()
                else -> isEdge()
            }

        fun isCore(): (Router) -> Boolean = { router -> router.routerType == RouterType.CORE }

        fun isEdge(): (Router) -> Boolean = { router -> router.routerType == RouterType.EDGE }
    }
}
