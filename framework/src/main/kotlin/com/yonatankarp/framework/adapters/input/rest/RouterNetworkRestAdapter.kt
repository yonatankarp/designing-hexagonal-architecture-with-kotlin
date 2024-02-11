package com.yonatankarp.framework.adapters.input.rest

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpServer
import com.yonatankarp.application.usecases.RouterNetworkUseCase
import com.yonatankarp.domain.entity.Router
import com.yonatankarp.framework.adapters.input.RouterNetworkAdapter
import com.yonatankarp.framework.adapters.input.rest.RouterNetworkRestAdapter.HttpStatus.*
import com.yonatankarp.framework.adapters.output.file.mappers.RouterJsonFileMapper.toJson
import java.net.URLDecoder

class RouterNetworkRestAdapter(
    routerNetworkUseCase: RouterNetworkUseCase,
) : RouterNetworkAdapter(routerNetworkUseCase) {

    override fun processRequest(requestParams: Any): Router? {
        var router: Router? = null
        if (requestParams is HttpServer) {
            requestParams.createContext("/network/add") { exchange ->
                router = handleAddNetwork(exchange)
            }

            requestParams.apply {
                executor = null
                start()
            }
        }
        return router
    }

    private fun handleAddNetwork(exchange: HttpExchange): Router? =
        when (exchange.requestMethod) {
            "GET" -> {
                val query = exchange.requestURI.rawQuery
                val params = httpParams(query)
                val router = addNetworkToRouter(params)
                val mapper = jacksonObjectMapper()
                val routerJson = mapper.writeValueAsString(router?.toJson())
                exchange.responseHeaders.set(
                    "Content-Type",
                    "application/json",
                )
                exchange.sendResponseHeaders(
                    OK.value,
                    routerJson.toByteArray().size.toLong(),
                )
                val output = exchange.responseBody
                output.write(routerJson.toByteArray())
                output.flush()
                exchange.close()
                router
            }

            else -> {
                exchange.sendResponseHeaders(
                    METHOD_NOT_ALLOWED.value,
                    NOT_CONTENT_BODY
                )
                exchange.close()
                null
            }
        }

    private fun httpParams(query: String): Map<String, String> {
        val noNameText = "Anonymous"

        val requestParams = parseRequestParameters(query)

        val routerId = requestParams.getValue("routerId", noNameText)
        val address = requestParams.getValue("address", noNameText)
        val name = requestParams.getValue("name", noNameText)
        val cidr = requestParams.getValue("cidr", noNameText)

        return mapOf(
            "routerId" to routerId,
            "address" to address,
            "name" to name,
            "cidr" to cidr
        )
    }

    private fun Map<String, List<String>>.getValue(
        key: String,
        default: String
    ) = getOrDefault(key, listOf(default)).firstOrDefault(default)


    private fun <T> List<T>.firstOrDefault(t: T) = firstOrNull() ?: t

    private fun parseRequestParameters(query: String) =
        "&".toRegex().splitToSequence(query)
            .map {
                it.split("=")
                    .dropLastWhile { it.isEmpty() }
                    .toTypedArray()
                    .copyOf(2)
            }
            .groupBy { decode(it[0]) }
            .mapValues { (_, values) ->
                values.map { decode(it[1]) }
            }

    enum class HttpStatus(val value: Int) {
        OK(200),
        METHOD_NOT_ALLOWED(405)
    }

    companion object {
        private const val NOT_CONTENT_BODY = -1L

        private fun decode(encoded: String?) =
            try {
                URLDecoder.decode(encoded, "UTF-8")
            } catch (e: Exception) {
                throw RuntimeException("UTF-8 is a required encoding", e)
            }
    }
}
