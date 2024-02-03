package com.yonatankarp.domain.specification.shared

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class OrSpecificationTest {
    @Test
    fun `should be satisfied when both specifications are satisfied`() {
        // Given
        val spec1 = mockk<Specification<Int>>()
        val spec2 = mockk<Specification<Int>>()

        every { spec1.isSatisfiedBy(any()) } returns true
        every { spec2.isSatisfiedBy(any()) } returns true

        val orSpecification = OrSpecification(spec1, spec2)

        // When
        val result = orSpecification.isSatisfiedBy(123)

        // Then
        assertTrue(result)
    }

    @Test
    fun `should be satisfied when only first specifications is satisfied`() {
        // Given
        val spec1 = mockk<Specification<Int>>()
        val spec2 = mockk<Specification<Int>>()

        every { spec1.isSatisfiedBy(any()) } returns true
        every { spec2.isSatisfiedBy(any()) } returns false

        val orSpecification = OrSpecification(spec1, spec2)

        // When
        val result = orSpecification.isSatisfiedBy(123)

        // Then
        assertTrue(result)
    }

    @Test
    fun `should be satisfied when only second specifications is satisfied`() {
        // Given
        val spec1 = mockk<Specification<Int>>()
        val spec2 = mockk<Specification<Int>>()

        every { spec1.isSatisfiedBy(any()) } returns false
        every { spec2.isSatisfiedBy(any()) } returns true

        val orSpecification = OrSpecification(spec1, spec2)

        // When
        val result = orSpecification.isSatisfiedBy(123)

        // Then
        assertTrue(result)
    }

    @Test
    fun `should not be satisfied when both specifications are not satisfied`() {
        // Given
        val spec1 = mockk<Specification<Int>>()
        val spec2 = mockk<Specification<Int>>()

        every { spec1.isSatisfiedBy(any()) } returns false
        every { spec2.isSatisfiedBy(any()) } returns false

        val orSpecification = OrSpecification(spec1, spec2)

        // When
        val result = orSpecification.isSatisfiedBy(123)

        // Then
        assertFalse(result)
    }
}
