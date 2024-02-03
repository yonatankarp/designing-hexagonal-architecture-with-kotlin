package com.yonatankarp.domain.policy

import com.yonatankarp.domain.entry.EventFixture
import com.yonatankarp.domain.valueobject.EventId
import com.yonatankarp.domain.valueobject.Protocol
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalTime
import java.time.OffsetTime
import java.time.ZoneOffset
import kotlin.test.Test

class SplitEventParserTest {
    @Test
    fun `should parse correctly IPv4 log event`() {
        // Given
        val parser = SplitEventParser()
        val logLine = EventFixture.ipv4LogEvent

        // When
        val event = parser.parseEvent(logLine)

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
        assertEquals("menuvivofibra.br.domain", event.activity.srcHost)
        assertEquals("casanova.58183", event.activity.dstHost)
    }

    @Test
    fun `should parse correctly iPv6 log event`() {
        // Given
        val parser = SplitEventParser()
        val logLine = EventFixture.ipv6LogEvent

        // When
        val event = parser.parseEvent(logLine)

        // Then
        assertEquals(
            OffsetTime.of(
                LocalTime.parse("00:44:06.906367"),
                ZoneOffset.UTC,
            ),
            event.timestamp,
        )
        assertEquals(EventId.of("100430035020260940012015"), event.id)
        assertEquals(Protocol.IPV6, event.protocol)
        assertEquals("casanova.58183", event.activity.srcHost)
        assertEquals("menuvivofibra.br.domain", event.activity.dstHost)
    }
}
