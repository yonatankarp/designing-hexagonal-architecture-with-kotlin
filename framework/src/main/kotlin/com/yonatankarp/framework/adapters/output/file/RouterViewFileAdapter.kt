package com.yonatankarp.framework.adapters.output.file

import com.yonatankarp.application.ports.output.RouterViewOutputPort
import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.entity.Switch
import com.yonatankarp.domain.valueobject.IP
import com.yonatankarp.domain.valueobject.RouterId
import com.yonatankarp.domain.valueobject.RouterType
import com.yonatankarp.domain.valueobject.SwitchId
import com.yonatankarp.domain.valueobject.SwitchType
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.UUID

object RouterViewFileAdapter : RouterViewOutputPort {
    override fun fetchRouters(): List<Router> {
        return readFileAsString()
    }

    private fun readFileAsString(): List<Router> {
        return runCatching {
            BufferedReader(
                InputStreamReader(
                    RouterViewFileAdapter::class.java.classLoader.getResourceAsStream("routers.txt")!!,
                ),
            ).lines().use { stream ->
                stream
                    .map { line ->
                        val (id, type) = line.split(";")
                        Router(RouterType.valueOf(type), RouterId.withId(UUID.fromString(id)), emptySwitch)
                    }
                    .toList()
            }
        }
            .onFailure { it.printStackTrace() }
            .getOrNull() ?: emptyList()
    }

    private val emptySwitch =
        Switch(
            networks = emptyList(),
            switchType = SwitchType.LAYER2,
            switchId = SwitchId.withoutId(),
            address = IP("1.2.3.4"),
        )
}
