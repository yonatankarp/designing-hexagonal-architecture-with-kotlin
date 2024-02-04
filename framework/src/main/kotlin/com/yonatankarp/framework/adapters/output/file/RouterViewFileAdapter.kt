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
                        Router(RouterType.valueOf(type), RouterId.withId(id), emptySwitch)
                    }
                    .toList()
            }
        }
            .onFailure { it.printStackTrace() }
            .getOrNull() ?: emptyList()
    }

    private val emptySwitch: Switch
        get() =
            Switch(
                emptyList(),
                SwitchType.LAYER2,
                SwitchId.withoutId(),
                IP("0.0.0.0"),
            )
}
