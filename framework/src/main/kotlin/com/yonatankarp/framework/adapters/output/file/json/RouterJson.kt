package com.yonatankarp.framework.adapters.output.file.json

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

@JsonInclude(value = JsonInclude.Include.NON_NULL)
data class RouterJson(
    @JsonProperty("routerId")
    val routerId: UUID,
    @JsonProperty("routerType")
    val routerType: RouterTypeJson,
    @JsonProperty("switch")
    val networkSwitch: SwitchJson,
)
