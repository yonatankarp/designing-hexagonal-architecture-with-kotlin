package com.yonatankarp.domain.service

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.entry.RouterFixture
import com.yonatankarp.domain.valueobject.RouterType
import kotlin.test.Test
import kotlin.test.assertEquals

class RouterSearchTest {
    @Test
    fun `should return a list of matching routers`() {
        // Given
        val routers =
            listOf(
                RouterFixture.randomEdgeRouter,
                RouterFixture.coreRouter,
                RouterFixture.randomEdgeRouter,
            )

        // When
        val matchingRouters = RouterSearch.retrieveRouter(routers) { router -> router.routerType == RouterType.CORE }

        // Then
        assertEquals(1, matchingRouters.size)

        val router = matchingRouters.first()
        assertEquals(RouterFixture.coreRouter.routerId, router.routerId)
    }

    @Test
    fun `should return empty list when list of routers is empty`() {
        // Given
        val routers = emptyList<Router>()

        // When
        val matchingRouters = RouterSearch.retrieveRouter(routers) { router -> router.routerType == RouterType.CORE }

        // Then
        assertEquals(0, matchingRouters.size)
    }
}
