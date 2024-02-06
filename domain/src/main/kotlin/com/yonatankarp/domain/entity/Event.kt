package com.yonatankarp.domain.entity

import com.yonatankarp.domain.policy.EventParserFactory
import com.yonatankarp.domain.valueobject.Activity
import com.yonatankarp.domain.valueobject.EventId
import com.yonatankarp.domain.valueobject.ParsePolicyType
import com.yonatankarp.domain.valueobject.Protocol
import java.time.OffsetTime

data class Event(
    val timestamp: OffsetTime,
    val id: EventId,
    val protocol: Protocol,
    val activity: Activity,
) : Comparable<Event> {
    override fun compareTo(other: Event) = timestamp.compareTo(other.timestamp)

    companion object {
        fun parsedEvent(
            unparsedEvent: String,
            policy: ParsePolicyType,
        ): Event = EventParserFactory.eventParser(policy).parseEvent(unparsedEvent)
    }
}
