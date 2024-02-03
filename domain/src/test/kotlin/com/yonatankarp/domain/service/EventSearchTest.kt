package com.yonatankarp.domain.service

import com.yonatankarp.domain.entry.EventFixture
import com.yonatankarp.domain.valueobject.EventId
import com.yonatankarp.domain.valueobject.ParsePolicyType
import com.yonatankarp.domain.valueobject.Protocol
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class EventSearchTest {
    @ParameterizedTest
    @EnumSource(ParsePolicyType::class, names = ["REGEX", "SPLIT"])
    fun `should return a list of valid Events`(policy: ParsePolicyType) {
        // Given
        val eventSearch = EventSearch()

        val unparsedEvents =
            listOf(
                EventFixture.ipv4LogEvent,
                EventFixture.ipv6LogEvent,
            )

        // When
        val events = eventSearch.retrieveEvents(unparsedEvents, policy)

        // Then the list of retrieved events should match the expected list
        assertEquals(2, events.size)

        val event1 = events[0]
        assertEquals(EventId.of("100430035020260940012016"), event1.id)
        assertEquals(Protocol.IPV4, event1.protocol)
        assertEquals("menuvivofibra.br.domain", event1.activity.srcHost)
        assertEquals("casanova.58183", event1.activity.dstHost)

        val event2 = events[1]
        assertEquals(EventId.of("100430035020260940012015"), event2.id)
        assertEquals(Protocol.IPV6, event2.protocol)
        assertEquals("casanova.58183", event2.activity.srcHost)
        assertEquals("menuvivofibra.br.domain", event2.activity.dstHost)
    }
}
