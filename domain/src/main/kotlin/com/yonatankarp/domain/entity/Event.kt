package com.yonatankarp.domain.entity

import com.yonatankarp.domain.policy.RegexEventParser
import com.yonatankarp.domain.policy.SplitEventParser
import com.yonatankarp.domain.valueobject.Activity
import com.yonatankarp.domain.valueobject.EventId
import com.yonatankarp.domain.valueobject.ParsePolicyType
import com.yonatankarp.domain.valueobject.ParsePolicyType.REGEX
import com.yonatankarp.domain.valueobject.ParsePolicyType.SPLIT
import com.yonatankarp.domain.valueobject.Protocol
import java.time.OffsetDateTime

data class Event(
    private val timestamp: OffsetDateTime,
    private val id: EventId,
    private val protocol: Protocol,
    private val activity: Activity,
) : Comparable<Event> {
    override fun compareTo(other: Event) = timestamp.compareTo(other.timestamp)

    companion object {
        fun parsedEvent(
            unparsedEvent: String,
            policy: ParsePolicyType,
        ): Event {
            return when (policy) {
                REGEX -> RegexEventParser().parseEvent(unparsedEvent)
                SPLIT -> SplitEventParser().parseEvent(unparsedEvent)
            }
        }
    }
}
