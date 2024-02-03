package com.yonatankarp.domain.entry

object EventFixture {
    val ipv4LogEvent: String
        get() =
            "00:44:06.912775 100430035020260940012016 IPV4" +
                " menuvivofibra.br.domain > casanova.58183: 64865 1/0/0 PTR" +
                " all-systems.mcast.net. (75)"

    val ipv6LogEvent: String
        get() =
            "00:44:06.906367 100430035020260940012015 IPV6 casanova.58183" +
                " > menuvivofibra.br.domain: 64865+ PTR? 1.0.0.224.in-addr.arpa." +
                " (40)"
}
