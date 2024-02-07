package com.yonatankarp.application.bdd

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.entity.Switch
import com.yonatankarp.domain.valueobject.IP
import com.yonatankarp.domain.valueobject.Network
import com.yonatankarp.domain.valueobject.RouterId
import com.yonatankarp.domain.valueobject.RouterType
import com.yonatankarp.domain.valueobject.SwitchId
import com.yonatankarp.domain.valueobject.SwitchType

object SampleRouterView {
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

    fun fetchRouterById(routerId: RouterId): Router? = routers.find { it.routerId == routerId }

    fun persistRouter(router: Router): Boolean = routers.add(router)
}
