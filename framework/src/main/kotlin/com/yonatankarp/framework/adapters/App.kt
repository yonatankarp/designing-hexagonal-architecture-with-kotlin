package com.yonatankarp.framework.adapters

import com.yonatankarp.framework.adapters.input.stdin.RouterViewCLIAdapter

fun main(vararg args: String) {
    val cli = RouterViewCLIAdapter()
    val type = "CORE"
    println(cli.obtainRelatedRouters(type))
}
