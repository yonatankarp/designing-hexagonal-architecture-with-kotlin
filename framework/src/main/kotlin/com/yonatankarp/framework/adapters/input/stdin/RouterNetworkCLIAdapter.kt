package com.yonatankarp.framework.adapters.input.stdin

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.yonatankarp.application.usecases.RouterNetworkUseCase
import com.yonatankarp.domain.entity.Router
import com.yonatankarp.framework.adapters.input.RouterNetworkAdapter
import com.yonatankarp.framework.adapters.output.file.mappers.RouterJsonFileMapper.toJson
import java.util.Scanner

class RouterNetworkCLIAdapter(
    routerNetworkUseCase: RouterNetworkUseCase,
) : RouterNetworkAdapter(routerNetworkUseCase) {
    override fun processRequest(requestParams: Any): Router? {
        val params = stdinParams(requestParams)
        val router = addNetworkToRouter(params)
        val mapper = jacksonObjectMapper()

        try {
            val routerJson = mapper.writeValueAsString(router?.toJson())
            println(routerJson)
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }

        return router
    }

    private fun stdinParams(requestParams: Any): Map<String, String> {
        if (requestParams !is Scanner) throw IllegalArgumentException("Request with invalid parameters")

        val routerId = getParam(requestParams, "Please inform the Router ID:")
        val address = getParam(requestParams, "Please inform the IP address:")
        val name = getParam(requestParams, "Please inform the Network Name:")
        val cidr = getParam(requestParams, "Please inform the CIDR:")

        return mapOf(
            "routerId" to routerId,
            "address" to address,
            "name" to name,
            "cidr" to cidr,
        )
    }

    private fun getParam(
        scanner: Scanner,
        message: String,
    ): String {
        println(message)
        return scanner.nextLine()
    }
}
