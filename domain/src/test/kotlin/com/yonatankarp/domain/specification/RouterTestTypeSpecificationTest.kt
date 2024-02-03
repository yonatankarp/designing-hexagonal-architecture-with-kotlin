package com.yonatankarp.domain.specification

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.valueobject.RouterType
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RouterTestTypeSpecificationTest {
    @ParameterizedTest(name = "Given a router with {0} type, When checking specification, Then it should be satisfied")
    @EnumSource(RouterType::class, names = ["CORE", "EDGE"])
    fun `router should satisfy specification`(type: RouterType) {
        // Given
        val router = mockk<Router>()
        every { router.routerType } returns type
        val specification = RouterTypeSpecification()

        // When
        val result = specification.isSatisfiedBy(router)

        // Then
        assertTrue(result)
    }

    @ParameterizedTest(name = "Given a router with {0} type, When checking specification, Then it should not be satisfied")
    @EnumSource(RouterType::class, names = ["DISTRIBUTION", "BACKBONE"])
    fun `router should not satisfy specification`(type: RouterType) {
        // Given
        val router = mockk<Router>()
        every { router.routerType } returns type
        val specification = RouterTypeSpecification()

        // When
        val result = specification.isSatisfiedBy(router)

        // Then
        assertFalse(result)
    }
}
