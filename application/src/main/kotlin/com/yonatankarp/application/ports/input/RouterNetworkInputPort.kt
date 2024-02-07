package com.yonatankarp.application.ports.input

import com.yonatankarp.application.ports.output.RouterNetworkOutputPort
import com.yonatankarp.application.usecases.RouterNetworkUseCase
import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.service.NetworkOperation
import com.yonatankarp.domain.valueobject.Network
import com.yonatankarp.domain.valueobject.RouterId

class RouterNetworkInputPort(
    private val routerNetworkOutputPort: RouterNetworkOutputPort,
) : RouterNetworkUseCase {
    override fun addNetworkToRouter(
        routerId: RouterId,
        network: Network,
    ): Router? = fetchRouter(routerId)?.let { createNetwork(it, network) }

    private fun fetchRouter(routerId: RouterId): Router? = routerNetworkOutputPort.fetchRouterById(routerId)

    private fun createNetwork(
        router: Router,
        network: Network,
    ): Router? {
        val newRouter = NetworkOperation.createNetWork(router, network)
        return if (persistNetwork(router)) newRouter else router // FIXME: This is weird! check if it make sense after chapter 4!
    }

    private fun persistNetwork(router: Router): Boolean = routerNetworkOutputPort.persistRouter(router)
}
