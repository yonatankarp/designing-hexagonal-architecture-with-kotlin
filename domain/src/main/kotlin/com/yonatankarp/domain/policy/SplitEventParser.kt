package com.yonatankarp.domain.policy

import com.yonatankarp.domain.entity.Event
import com.yonatankarp.domain.policy.EventParser.Companion.formatter
import com.yonatankarp.domain.valueobject.Activity
import com.yonatankarp.domain.valueobject.EventId
import com.yonatankarp.domain.valueobject.Protocol
import java.time.LocalTime
import java.time.ZoneOffset

class SplitEventParser : EventParser {
    override fun parseEvent(event: String): Event {
        val fields = event.split(" ")
        return Event(
            timestamp = LocalTime.parse(fields[0], formatter).atOffset(ZoneOffset.UTC),
            id = EventId.of(fields[1]),
            protocol = Protocol.valueOf(fields[2]),
            activity = Activity(fields[3], fields[5].dropLast(1)),
        )
    }
}
