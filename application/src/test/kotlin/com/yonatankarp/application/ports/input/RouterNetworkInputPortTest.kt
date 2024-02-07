package com.yonatankarp.application.ports.input

import com.yonatankarp.application.ports.output.RouterNetworkOutputPort
import com.yonatankarp.domain.entry.RouterFixture
import com.yonatankarp.domain.valueobject.NetworkFixture
import com.yonatankarp.domain.valueobject.RouterId
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RouterNetworkInputPortTest {
    @Test
    fun `Given router and network exist - When adding network to router - Then the router contains the new network`() {
        // Given
        val outputPort = mockk<RouterNetworkOutputPort>()
        val inputPort = RouterNetworkInputPort(outputPort)
        val routerId = RouterId.withoutId()
        val router = RouterFixture.coreRouter
        val network = NetworkFixture.randomValidNetworkIpv4

        every { outputPort.fetchRouterById(routerId) } returns router
        every { outputPort.persistRouter(router) } returns true

        // When
        val result = inputPort.addNetworkToRouter(routerId, network)

        // Then
        assertTrue(result!!.networkSwitch.networks.contains(network))
    }

    @Test
    fun `Given router does not exist - When adding network to router - Then return null`() {
        // Given
        val outputPort = mockk<RouterNetworkOutputPort>()
        val inputPort = RouterNetworkInputPort(outputPort)
        val routerId = RouterId.withoutId()
        val network = NetworkFixture.validNetworkIPv4

        every { outputPort.fetchRouterById(routerId) } returns null

        // When
        val resultRouter = inputPort.addNetworkToRouter(routerId, network)

        // Then
        assertNull(resultRouter)
    }

    @Test
    fun `Given network can be persisted - When adding network to router - Then return new router`() {
        // Given
        val outputPort = mockk<RouterNetworkOutputPort>()
        val inputPort = RouterNetworkInputPort(outputPort)
        val routerId = RouterId.withoutId()
        val network = NetworkFixture.randomValidNetworkIpv4
        val router = RouterFixture.coreRouter

        every { outputPort.fetchRouterById(routerId) } returns router
        every { outputPort.persistRouter(router) } returns true

        // When
        val result = inputPort.addNetworkToRouter(routerId, network)

        // Then
        assertNotNull(result)
        assertNotEquals(router, result)
        assertTrue(result!!.networkSwitch.networks.contains(network))
    }

    @Test
    fun `Given network cannot be persisted, When adding network to router, Then return original router`() {
        // Given
        val outputPort = mockk<RouterNetworkOutputPort>()
        val inputPort = RouterNetworkInputPort(outputPort)
        val routerId = RouterId.withoutId()
        val network = NetworkFixture.randomValidNetworkIpv4
        val router = RouterFixture.coreRouter

        every { outputPort.fetchRouterById(routerId) } returns router
        every { outputPort.persistRouter(router) } returns false

        // When
        val result = inputPort.addNetworkToRouter(routerId, network)

        // Then
        assertEquals(router, result)
        assertFalse(result!!.networkSwitch.networks.contains(network))
    }
}
