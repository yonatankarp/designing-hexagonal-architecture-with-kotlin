package com.yonatankarp.domain.specification

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.entry.SwitchFixture
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class NetworkAmountSpecificationTest {
    @Test
    fun `should be satisfied when router have less than maximum allowed networks`() {
        // Given
        val router = mockk<Router>()
        every { router.networkSwitch } returns SwitchFixture.singleNetworkSwitch

        val specification = NetworkAmountSpecification()

        // When
        val result = specification.isSatisfiedBy(router)

        // Then
        assertTrue(result)
    }

    @Test
    fun `should be satisfied when router have no networks`() {
        // Given
        val router = mockk<Router>()
        every { router.networkSwitch } returns SwitchFixture.emptySwitch

        val specification = NetworkAmountSpecification()

        // When
        val result = specification.isSatisfiedBy(router)

        // Then
        assertTrue(result)
    }

    @Test
    fun `should be satisfied when router have exactly maximum allowed number of networks`() {
        // Given
        val router = mockk<Router>()
        every { router.networkSwitch } returns SwitchFixture.multipleNetworkSwitch

        val specification = NetworkAmountSpecification()

        // When
        val result = specification.isSatisfiedBy(router)

        // Then
        assertTrue(result)
    }

    @Test
    fun `should not be satisfied when router have more than maximum allowed number of networks`() {
        // Given
        val router = mockk<Router>()

        every { router.networkSwitch } returns SwitchFixture.overMaximumAllowedNetworksSwitch

        val specification = NetworkAmountSpecification()

        // When
        val result = specification.isSatisfiedBy(router)

        // Then
        assertFalse(result)
    }
}
