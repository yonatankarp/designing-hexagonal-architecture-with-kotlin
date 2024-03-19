package com.yonatankarp.framework.adapters.output.file.json

import com.fasterxml.jackson.annotation.JsonProperty

data class NetworkJson(
    @JsonProperty("ip")
    val ip: IPJson,
    @JsonProperty("networkName")
    val networkName: String,
    @JsonProperty("networkCidr")
    val cidr: UInt,
)
