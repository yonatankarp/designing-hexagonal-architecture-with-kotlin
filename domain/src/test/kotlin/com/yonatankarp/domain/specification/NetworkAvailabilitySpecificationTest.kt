package com.yonatankarp.domain.specification

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.entry.SwitchFixture
import com.yonatankarp.domain.valueobject.IPFixture
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class NetworkAvailabilitySpecificationTest {
    @Test
    fun `should be satisfied when new required network IP is not included in the network`() {
        // Given
        val router = mockk<Router>()
        every { router.networkSwitch } returns SwitchFixture.singleNetworkSwitch

        val specification =
            NetworkAvailabilitySpecification(
                IPFixture.randomIPv4,
                "New Network",
                16u,
            )

        // When
        val result = specification.isSatisfiedBy(router)

        // Then
        assertTrue(result)
    }

    @Test
    fun `should not be satisfied when new required network IP is matching an existing network`() {
        // Given
        val router = mockk<Router>()
        val switch = SwitchFixture.multipleNetworkSwitch
        every { router.networkSwitch } returns switch

        val matchingNetwork = switch.networks.first()
        val specification =
            NetworkAvailabilitySpecification(
                matchingNetwork.address,
                matchingNetwork.name,
                matchingNetwork.cidr,
            )

        // When
        val result = specification.isSatisfiedBy(router)

        // Then
        assertFalse(result)
    }

    @Test
    fun `should not be satisfied when router is null`() {
        // Given
        val router = null
        val matchingNetwork = SwitchFixture.multipleNetworkSwitch.networks.first()
        val specification =
            NetworkAvailabilitySpecification(
                matchingNetwork.address,
                matchingNetwork.name,
                matchingNetwork.cidr,
            )

        // When
        val result = specification.isSatisfiedBy(router)

        // Then
        assertFalse(result)
    }
}
