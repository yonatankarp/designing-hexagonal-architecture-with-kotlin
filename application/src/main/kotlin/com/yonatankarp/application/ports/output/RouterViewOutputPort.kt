package com.yonatankarp.application.ports.output

import com.yonatankarp.domain.entity.Router

fun interface RouterViewOutputPort {
    fun fetchRouters(): List<Router>
}
