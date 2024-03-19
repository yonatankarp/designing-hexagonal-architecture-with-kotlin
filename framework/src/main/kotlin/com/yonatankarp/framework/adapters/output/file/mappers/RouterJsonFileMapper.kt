package com.yonatankarp.framework.adapters.output.file.mappers

import com.yonatankarp.domain.entity.Router
import com.yonatankarp.domain.entity.Switch
import com.yonatankarp.domain.valueobject.IP
import com.yonatankarp.domain.valueobject.Network
import com.yonatankarp.domain.valueobject.RouterId
import com.yonatankarp.domain.valueobject.RouterType
import com.yonatankarp.domain.valueobject.SwitchId
import com.yonatankarp.domain.valueobject.SwitchType
import com.yonatankarp.framework.adapters.output.file.json.IPJson
import com.yonatankarp.framework.adapters.output.file.json.NetworkJson
import com.yonatankarp.framework.adapters.output.file.json.RouterJson
import com.yonatankarp.framework.adapters.output.file.json.RouterTypeJson
import com.yonatankarp.framework.adapters.output.file.json.SwitchJson
import com.yonatankarp.framework.adapters.output.file.json.SwitchTypeJson

object RouterJsonFileMapper {
    fun RouterJson.toDomain(): Router {
        val routerId = RouterId.withId(routerId.toString())
        val routerType = RouterType.valueOf(routerType.name)
        val switchId = SwitchId.withId(networkSwitch.switchId.toString())
        val switchType = SwitchType.valueOf(networkSwitch.switchType.toString())
        val ip = IP.fromAddress(networkSwitch.ip.address)
        val networks = networkSwitch.networks.toDomain()

        val networkSwitch = Switch(networks, switchType, switchId, ip)

        return Router(routerType, routerId, networkSwitch)
    }

    private fun List<NetworkJson>.toDomain(): List<Network> =
        map { json ->
            Network(
                IP.fromAddress(json.ip.address),
                json.networkName,
                json.cidr,
            )
        }

    fun Router.toJson(): RouterJson {
        val routerId = routerId.id
        val routerTypeJson = RouterTypeJson.valueOf(routerType.toString())
        val switchIdJson = networkSwitch.switchId.id
        val switchTypeJson =
            SwitchTypeJson.valueOf(networkSwitch.switchType.toString())
        val ipJson = IPJson.fromAddress(networkSwitch.address.address)
        val networkDataList = networkSwitch.networks.toJson()

        val switchJson =
            SwitchJson(switchIdJson, ipJson, switchTypeJson, networkDataList)

        return RouterJson(routerId, routerTypeJson, switchJson)
    }

    private fun List<Network>.toJson(): List<NetworkJson> =
        map { network ->
            NetworkJson(
                IPJson.fromAddress(network.address.address),
                network.name,
                network.cidr,
            )
        }
}
