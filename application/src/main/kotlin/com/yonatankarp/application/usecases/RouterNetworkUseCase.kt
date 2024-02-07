package com.yonatankarp.application.usecases

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.valueobject.Network
import com.yonatankarp.domain.valueobject.RouterId

fun interface RouterNetworkUseCase {
    fun addNetworkToRouter(
        routerId: RouterId,
        network: Network,
    ): Router?
}
