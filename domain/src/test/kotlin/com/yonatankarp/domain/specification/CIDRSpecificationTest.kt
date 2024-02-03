package com.yonatankarp.domain.specification

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class CIDRSpecificationTest {
    @Test
    fun `should be satisfied when CIDR is greater than minimum allowed`() {
        // Given
        val specification = CIDRSpecification()

        // When
        val result = specification.isSatisfiedBy(12u)

        // Then
        assertTrue(result)
    }

    @Test
    fun `should be satisfied when CIDR is equal than minimum allowed`() {
        // Given
        val specification = CIDRSpecification()

        // When
        val result = specification.isSatisfiedBy(CIDRSpecification.MINIMUM_ALLOWED_CIDR + 1u)

        // Then
        assertTrue(result)
    }

    @Test
    fun `should not be satisfied when CIDR is greater than minimum allowed`() {
        // Given
        val specification = CIDRSpecification()

        // When
        val result = specification.isSatisfiedBy(4u)

        // Then
        assertFalse(result)
    }
}
