package com.yonatankarp.domain.policy

import com.yonatankarp.domain.valueobject.ParsePolicyType
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class EventParserFactoryTest {
    @ParameterizedTest(name = "should return the correct parser for policy {0}")
    @MethodSource("testCases")
    fun `should return the correct parser`(
        parsePolicy: ParsePolicyType,
        clazz: Class<EventParser>,
    ) {
        // Given
        val policy = parsePolicy

        // When
        val eventParser = EventParserFactory.eventParser(policy)

        // Then
        assertEquals(clazz, eventParser::class.java)
    }

    companion object {
        @JvmStatic
        fun testCases() =
            listOf(
                Arguments.of(ParsePolicyType.SPLIT, SplitEventParser::class.java),
                Arguments.of(ParsePolicyType.REGEX, RegexEventParser::class.java),
            )
    }
}
