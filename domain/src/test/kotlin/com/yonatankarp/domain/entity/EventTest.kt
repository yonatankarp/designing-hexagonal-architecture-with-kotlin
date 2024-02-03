package com.yonatankarp.domain.entity

import com.yonatankarp.domain.entry.EventFixture
import com.yonatankarp.domain.valueobject.Activity
import com.yonatankarp.domain.valueobject.EventId
import com.yonatankarp.domain.valueobject.ParsePolicyType
import com.yonatankarp.domain.valueobject.Protocol
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import java.time.LocalTime
import java.time.OffsetTime
import java.time.ZoneOffset

class EventTest {
    @ParameterizedTest
    @EnumSource(ParsePolicyType::class, names = ["REGEX", "SPLIT"])
    fun `should return a valid Event for REGEX configuration`(policy: ParsePolicyType) {
        // Given
        val unparsedEvent = EventFixture.ipv4LogEvent

        // When
        val event = Event.parsedEvent(unparsedEvent, policy)

        // Then
        assertEquals(
            OffsetTime.of(
                LocalTime.parse("00:44:06.912775"),
                ZoneOffset.UTC,
            ),
            event.timestamp,
        )
        assertEquals(EventId.of("100430035020260940012016"), event.id)
        assertEquals(Protocol.IPV4, event.protocol)
        assertEquals(Activity("menuvivofibra.br.domain", "casanova.58183"), event.activity)
    }
}
