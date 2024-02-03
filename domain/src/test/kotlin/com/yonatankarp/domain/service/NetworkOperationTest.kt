package com.yonatankarp.domain.service

import com.yonatankarp.domain.entry.RouterFixture
import com.yonatankarp.domain.valueobject.IPFixture
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class NetworkOperationTest {
    @Test
    fun `should add the network to the router when network is valid`() {
        // Given
        val networkOperation = NetworkOperation()
        val router = RouterFixture.emptyCoreRouter
        val address = IPFixture.randomIPv6
        val name = "Network1"
        val cidr = 24u

        // When
        networkOperation.createNetWork(router, address, name, cidr)

        // Then
        assertEquals(1, router.networkSwitch.networks.size)
        with(router.networkSwitch.networks.first()) {
            assertEquals(address, this.address)
            assertEquals(name, this.name)
            assertEquals(cidr, this.cidr)
        }
    }

    @Test
    fun `should throw IllegalArgumentException when CIDR value is invalid`() {
        // Given
        val router = RouterFixture.coreRouter
        val address = IPFixture.randomIPv4
        val name = "Network1"
        val invalidCidr = 7u

        // When
        val networkOperation = NetworkOperation()

        // Then
        val exception =
            assertThrows<IllegalArgumentException> {
                networkOperation.createNetWork(router, address, name, invalidCidr)
            }
        assertEquals("CIDR is below 8", exception.message)
    }
}
