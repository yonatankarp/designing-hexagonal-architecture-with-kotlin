package com.yonatankarp.framework.adapters.input.stdin

import com.yonatankarp.application.ports.input.RouterViewInputPort
import com.yonatankarp.application.usecases.RouterViewUseCase
import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.valueobject.RouterType
import com.yonatankarp.framework.adapters.output.file.RouterViewFileAdapter

class RouterViewCLIAdapter(private val routerViewUseCase: RouterViewUseCase = defaultAdapters()) {
    fun obtainRelatedRouters(type: String): List<Router> =
        routerViewUseCase.getRouters(
            Router.filterRouterByType(RouterType.valueOf(type)),
        )

    companion object {
        private fun defaultAdapters(): RouterViewUseCase = RouterViewInputPort(RouterViewFileAdapter)
    }
}
