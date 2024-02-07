package com.yonatankarp.application.ports.input

import com.yonatankarp.application.ports.output.RouterViewOutputPort
import com.yonatankarp.domain.entry.RouterFixture
import com.yonatankarp.domain.valueobject.RouterId
import com.yonatankarp.domain.valueobject.RouterType
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RouterViewInputPortTest {
    @Test
    fun `Given routers exist - When filtering routers - Then return filtered list`() {
        // Given
        val outputPort = mockk<RouterViewOutputPort>()
        val inputPort = RouterViewInputPort(outputPort)
        val routers = listOf(RouterFixture.coreRouter, RouterFixture.edgeRouter)

        every { outputPort.fetchRouters() } returns routers

        // When
        val filteredRouters = inputPort.getRouters { it.routerId == RouterId.withId("a8babbc5-699a-4e46-b490-a7b44e10cdf8") }

        // Then
        assertEquals(1, filteredRouters.size)
        assertEquals(RouterType.CORE, filteredRouters.first().routerType)
    }

    @Test
    fun `Given no routers exist - When filtering routers - Then return empty list`() {
        // Given
        val outputPort = mockk<RouterViewOutputPort>()
        val inputPort = RouterViewInputPort(outputPort)

        every { outputPort.fetchRouters() } returns emptyList()

        // When
        val filteredRouters = inputPort.getRouters { true }

        // Then
        assertEquals(0, filteredRouters.size)
    }
}
