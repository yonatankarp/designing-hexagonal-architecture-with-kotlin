package com.yonatankarp.domain.specification.shared

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class AndSpecificationTest {
    @Test
    fun `should be satisfied when both specifications are satisfied`() {
        // Given
        val spec1 = mockk<Specification<Int>>()
        val spec2 = mockk<Specification<Int>>()

        every { spec1.isSatisfiedBy(any()) } returns true
        every { spec2.isSatisfiedBy(any()) } returns true

        val andSpecification = AndSpecification(spec1, spec2)

        // When
        val result = andSpecification.isSatisfiedBy(123)

        // Then
        assertTrue(result)
    }

    @Test
    fun `should not be satisfied when first specifications is not satisfied`() {
        // Given
        val spec1 = mockk<Specification<Int>>()
        val spec2 = mockk<Specification<Int>>()

        every { spec1.isSatisfiedBy(any()) } returns true
        every { spec2.isSatisfiedBy(any()) } returns false

        val andSpecification = AndSpecification(spec1, spec2)

        // When
        val result = andSpecification.isSatisfiedBy(123)

        // Then
        assertFalse(result)
    }

    @Test
    fun `should not be satisfied when second specifications is not satisfied`() {
        // Given
        val spec1 = mockk<Specification<Int>>()
        val spec2 = mockk<Specification<Int>>()

        every { spec1.isSatisfiedBy(any()) } returns false
        every { spec2.isSatisfiedBy(any()) } returns true

        val andSpecification = AndSpecification(spec1, spec2)

        // When
        val result = andSpecification.isSatisfiedBy(123)

        // Then
        assertFalse(result)
    }

    @Test
    fun `should not be satisfied when both specifications are not satisfied`() {
        // Given
        val spec1 = mockk<Specification<Int>>()
        val spec2 = mockk<Specification<Int>>()

        every { spec1.isSatisfiedBy(any()) } returns false
        every { spec2.isSatisfiedBy(any()) } returns false

        val andSpecification = AndSpecification(spec1, spec2)

        // When
        val result = andSpecification.isSatisfiedBy(123)

        // Then
        assertFalse(result)
    }
}
