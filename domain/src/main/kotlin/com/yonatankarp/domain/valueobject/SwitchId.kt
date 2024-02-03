package com.yonatankarp.domain.valueobject

import java.util.UUID

data class SwitchId(val id: UUID) {
    companion object {
        fun withId(id: String) = SwitchId(UUID.fromString(id))

        fun withoutId() = SwitchId(UUID.randomUUID())
    }
}
