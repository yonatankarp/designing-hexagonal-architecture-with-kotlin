package com.yonatankarp.domain.valueobject

import java.util.UUID

@JvmInline
value class RouterId(val id: UUID) {
    override fun toString() = "RouterId{id='$id'}"

    companion object {
        fun withId(id: String) = RouterId(UUID.fromString(id))

        fun withoutId() = RouterId(UUID.randomUUID())
    }
}
