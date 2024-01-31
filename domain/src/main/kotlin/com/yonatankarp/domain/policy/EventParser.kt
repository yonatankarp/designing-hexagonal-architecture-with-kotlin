package com.yonatankarp.domain.policy

import com.yonatankarp.domain.entity.Event
import java.time.ZoneId
import java.time.format.DateTimeFormatter

interface EventParser {
    fun parseEvent(event: String): Event

    companion object {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").withZone(ZoneId.of("UTC"))
    }
}
