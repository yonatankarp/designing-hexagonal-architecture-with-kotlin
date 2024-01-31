package com.yonatankarp.domain.valueobject

import java.util.UUID

data class SwitchId private constructor(private val id: UUID) {
    override fun toString() = "SwitchId{id='$id'}"

    companion object {
        fun withId(id: String) = SwitchId(UUID.fromString(id))

        fun withoutId() = SwitchId(UUID.randomUUID())
    }
}
