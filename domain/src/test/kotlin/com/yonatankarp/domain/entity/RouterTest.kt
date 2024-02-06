package com.yonatankarp.domain.entity

import com.yonatankarp.domain.entry.RouterFixture
import com.yonatankarp.domain.entry.SwitchFixture
import com.yonatankarp.domain.valueobject.IPFixture
import com.yonatankarp.domain.valueobject.NetworkFixture
import com.yonatankarp.domain.valueobject.RouterId
import com.yonatankarp.domain.valueobject.RouterType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class RouterTest {
    @Test
    fun `should return a valid Network instance`() {
        // Given
        val router =
            Router(
                RouterType.CORE,
                RouterId.withoutId(),
                SwitchFixture.singleNetworkSwitch,
            )

        // When
        val network = router.createNetwork(IPFixture.validIPv4, "Network1", 24u)

        // Then
        assertEquals(IPFixture.validIPv4, network.address)
        assertEquals("Network1", network.name)
        assertEquals(24u, network.cidr)
    }

    @Test
    fun `should update the networkSwitch property`() {
        // Given
        val router =
            Router(
                RouterType.CORE,
                RouterId.withoutId(),
                SwitchFixture.singleNetworkSwitch,
            )
        val originalSwitch = router.networkSwitch
        val networkToAdd = NetworkFixture.randomValidNetworkIpv4

        // When
        router.addNetworkToSwitch(networkToAdd)

        // Then
        assertFalse(originalSwitch.networks.contains(networkToAdd))
        assertTrue(router.networkSwitch.networks.contains(networkToAdd))
    }

    @Test
    fun `should return the filter only core routers function`() {
        // Given
        val routerCore = RouterFixture.randomCoreRouter
        val routerEdge = RouterFixture.randomEdgeRouter

        // When
        val filterCore = Router.filterRouterByType(RouterType.CORE)

        // Then
        assertTrue(filterCore(routerCore))
        assertFalse(filterCore(routerEdge))
    }

    @Test
    fun `should return the filter only edge routers function`() {
        // Given
        val routerCore = RouterFixture.randomCoreRouter
        val routerEdge = RouterFixture.randomEdgeRouter

        // When
        val filterEdge = Router.filterRouterByType(RouterType.EDGE)

        // Then
        assertTrue(filterEdge(routerEdge))
        assertFalse(filterEdge(routerCore))
    }
}
