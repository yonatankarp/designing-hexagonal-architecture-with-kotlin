package com.yonatankarp.domain.valueobject

data class EventId private constructor(private val id: String) {
    companion object {
        fun of(id: String) = EventId(id)
    }
}
