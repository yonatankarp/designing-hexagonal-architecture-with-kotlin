package com.yonatankarp.domain.specification.shared

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.Test

class NotSpecificationTest {
    @Test
    fun `should be satisfied when specifications is not satisfied`() {
        // Given
        val spec = mockk<Specification<Int>>()

        every { spec.isSatisfiedBy(any()) } returns false

        val notSpecification = NotSpecification(spec)

        // When
        val result = notSpecification.isSatisfiedBy(123)

        // Then
        assertTrue(result)
    }

    @Test
    fun `should be not satisfied when specifications is satisfied`() {
        // Given
        val spec = mockk<Specification<Int>>()

        every { spec.isSatisfiedBy(any()) } returns true

        val notSpecification = NotSpecification(spec)

        // When
        val result = notSpecification.isSatisfiedBy(123)

        // Then
        assertFalse(result)
    }
}
