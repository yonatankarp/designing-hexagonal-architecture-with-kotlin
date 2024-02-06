package com.yonatankarp.framework.adapters.output.file

import com.yonatankarp.application.ports.output.RouterNetworkOutputPort
import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.entity.Switch
import com.yonatankarp.domain.valueobject.IP
import com.yonatankarp.domain.valueobject.Network
import com.yonatankarp.domain.valueobject.RouterId
import com.yonatankarp.domain.valueobject.RouterType
import com.yonatankarp.domain.valueobject.SwitchId
import com.yonatankarp.domain.valueobject.SwitchType

object RouterNetworkFileAdapter : RouterNetworkOutputPort {
    private val routers = mutableListOf<Router>()

    init {
        createSampleRouter()
    }

    private fun createSampleRouter() {
        val routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003")
        val network = Network(IP("10.0.0.0"), "HR", 8u)
        val networkSwitch =
            Switch(
                listOf(network),
                SwitchType.LAYER3,
                SwitchId.withoutId(),
                IP("9.0.0.9"),
            )
        val router = Router(RouterType.EDGE, routerId, networkSwitch)
        routers.add(router)
    }

    override fun fetchRouterById(routerId: RouterId): Router? = routers.find { it.routerId == routerId }

    override fun persistRouter(router: Router): Boolean = routers.add(router)
}
