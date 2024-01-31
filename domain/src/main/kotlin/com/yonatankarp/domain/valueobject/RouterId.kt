package com.yonatankarp.domain.valueobject

@JvmInline
value class RouterId(val id: String) {
    override fun toString() = "RouterId{id='$id'}"

    companion object {
        fun of(id: String) = RouterId(id)
    }
}
