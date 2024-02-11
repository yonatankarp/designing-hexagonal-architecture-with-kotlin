package com.yonatankarp.framework.adapters.output.file.json

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(value = JsonInclude.Include.NON_NULL)
enum class SwitchTypeJson {
    LAYER2,
    LAYER3,
}
