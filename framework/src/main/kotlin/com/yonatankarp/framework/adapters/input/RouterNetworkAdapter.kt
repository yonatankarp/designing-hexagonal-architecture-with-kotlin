package com.yonatankarp.framework.adapters.input

import com.yonatankarp.application.usecases.RouterNetworkUseCase
import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.valueobject.IP
import com.yonatankarp.domain.valueobject.Network
import com.yonatankarp.domain.valueobject.RouterId

abstract class RouterNetworkAdapter(
    protected val routerNetworkUseCase: RouterNetworkUseCase,
) {
    protected fun addNetworkToRouter(params: Map<String, String>): Router? {
        val routerId =
            requireNotNull(params["routerId"]) { "Router Id is missing." }

        val ip =
            IP.fromAddress(requireNotNull(params["address"]) { "Address is missing." })
        val name = requireNotNull(params["name"]) { "Name is missing." }
        val cidr =
            requireNotNull(params["cidr"]) { "CIDR is missing. " }.toUInt()

        val network = Network(ip, name, cidr)

        return routerNetworkUseCase.addNetworkToRouter(RouterId.withId(routerId), network)
    }

    abstract fun processRequest(requestParams: Any): Router?
}
