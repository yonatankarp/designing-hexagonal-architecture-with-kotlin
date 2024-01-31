package com.yonatankarp.domain.service

import com.yonatankarp.domain.entity.Router

object RouterSearch {
    fun retrieveRouter(
        routers: List<Router>,
        predicate: (Router) -> Boolean,
    ) = routers
        .filter(predicate)
        .toList()
}
