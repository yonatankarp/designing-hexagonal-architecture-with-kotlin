package com.yonatankarp.application.usecases

import com.yonatankarp.domain.entity.Router

fun interface RouterViewUseCase {
    fun getRouters(filter: (Router) -> Boolean): List<Router>
}
