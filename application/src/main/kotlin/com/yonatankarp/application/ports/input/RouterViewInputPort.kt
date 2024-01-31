package com.yonatankarp.application.ports.input

import com.yonatankarp.application.ports.output.RouterViewOutputPort
import com.yonatankarp.application.usecases.RouterViewUseCase
import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.service.RouterSearch

class RouterViewInputPort(
    private val routerListOutputPort: RouterViewOutputPort,
) : RouterViewUseCase {
    override fun getRouters(filter: (Router) -> Boolean): List<Router> {
        val routers = routerListOutputPort.fetchRouters()
        return RouterSearch.retrieveRouter(routers, filter)
    }
}
