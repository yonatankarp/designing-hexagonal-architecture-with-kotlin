package com.yonatankarp.domain.policy

import com.yonatankarp.domain.valueobject.ParsePolicyType

object EventParserFactory {
    fun eventParser(policy: ParsePolicyType) =
        when (policy) {
            ParsePolicyType.REGEX -> RegexEventParser()
            ParsePolicyType.SPLIT -> SplitEventParser()
        }
}
