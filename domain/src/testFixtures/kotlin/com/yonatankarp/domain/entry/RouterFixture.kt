package com.yonatankarp.domain.entry

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.valueobject.RouterId
import com.yonatankarp.domain.valueobject.RouterType

object RouterFixture {
    val emptyCoreRouter: Router
        get() =
            Router(
                RouterType.CORE,
                RouterId.withoutId(),
                SwitchFixture.emptySwitch,
            )

    val emptyEdgeRouter: Router
        get() =
            Router(
                RouterType.EDGE,
                RouterId.withoutId(),
                SwitchFixture.emptySwitch,
            )

    val coreRouter: Router
        get() =
            Router(
                RouterType.CORE,
                RouterId.withId("a8babbc5-699a-4e46-b490-a7b44e10cdf8"),
                SwitchFixture.singleNetworkSwitch,
            )

    val edgeRouter: Router
        get() =
            Router(
                RouterType.EDGE,
                RouterId.withId("53419d57-8f95-41b4-a46e-a22d7e764087"),
                SwitchFixture.singleNetworkSwitch,
            )

    val randomCoreRouter: Router
        get() =
            Router(
                RouterType.CORE,
                RouterId.withoutId(),
                SwitchFixture.singleNetworkSwitch,
            )

    val randomEdgeRouter: Router
        get() =
            Router(
                RouterType.EDGE,
                RouterId.withoutId(),
                SwitchFixture.singleNetworkSwitch,
            )
}
