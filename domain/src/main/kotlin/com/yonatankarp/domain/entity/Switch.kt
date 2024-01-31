package com.yonatankarp.domain.entity

import com.yonatankarp.domain.valueobject.IP
import com.yonatankarp.domain.valueobject.Network
import com.yonatankarp.domain.valueobject.SwitchId
import com.yonatankarp.domain.valueobject.SwitchType

data class Switch(
    val networks: List<Network>,
    val switchType: SwitchType,
    val switchId: SwitchId,
    val address: IP,
) {
    fun addNetwork(network: Network) = copy(networks = networks + network)
}
