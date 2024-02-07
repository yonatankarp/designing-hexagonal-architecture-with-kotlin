package com.yonatankarp.domain.service

import com.yonatankarp.domain.entry.RouterFixture
import com.yonatankarp.domain.valueobject.NetworkFixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class NetworkOperationTest {
    @Test
    fun `should add the network to the router when network is valid`() {
        // Given
        val router = RouterFixture.emptyCoreRouter
        val network = NetworkFixture.randomValidNetworkIpv4

        // When
        val result = NetworkOperation.createNetWork(router, network)

        // Then
        assertNotNull(result)
        assertEquals(1, result!!.networkSwitch.networks.size)
        with(result.networkSwitch.networks.first()) {
            assertEquals(address, this.address)
            assertEquals(name, this.name)
            assertEquals(cidr, this.cidr)
        }
    }

    @Test
    fun `should throw IllegalArgumentException when CIDR value is invalid for IPv4`() {
        // Given
        val router = RouterFixture.coreRouter
        val invalidNetwork = NetworkFixture.invalidCidrNetworkIpv4

        // When
        val exception =
            assertThrows<IllegalArgumentException> {
                NetworkOperation.createNetWork(router, invalidNetwork)
            }

        // Then
        assertEquals("CIDR is below 8", exception.message)
    }

    @Test
    fun `should throw IllegalArgumentException when CIDR value is invalid for IPv6`() {
        // Given
        val router = RouterFixture.coreRouter
        val invalidNetwork = NetworkFixture.invalidCidrNetworkIpv6

        // When
        val exception =
            assertThrows<IllegalArgumentException> {
                NetworkOperation.createNetWork(router, invalidNetwork)
            }

        // Then
        assertEquals("CIDR is below 8", exception.message)
    }
}
