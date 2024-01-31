package com.yonatankarp.domain.service

import com.yonatankarp.domain.entity.Event
import com.yonatankarp.domain.valueobject.ParsePolicyType

class EventSearch {
    fun retrieveEvents(
        unparsedEvents: List<String>,
        policyType: ParsePolicyType,
    ): List<Event> =
        unparsedEvents
            .map { event -> Event.parsedEvent(event, policyType) }
            .toList()
}
