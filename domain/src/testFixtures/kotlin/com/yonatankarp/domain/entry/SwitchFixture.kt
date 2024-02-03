package com.yonatankarp.domain.entry

import com.yonatankarp.domain.entity.Switch
import com.yonatankarp.domain.valueobject.IPFixture
import com.yonatankarp.domain.valueobject.NetworkFixture
import com.yonatankarp.domain.valueobject.SwitchIdFixture
import com.yonatankarp.domain.valueobject.SwitchType

object SwitchFixture {
    val emptySwitch: Switch
        get() =
            Switch(
                emptyList(),
                SwitchType.LAYER2,
                SwitchIdFixture.id,
                IPFixture.emptyIPv4,
            )

    val singleNetworkSwitch: Switch
        get() =
            Switch(
                listOf(NetworkFixture.validNetworkIPv4),
                SwitchType.LAYER2,
                SwitchIdFixture.id,
                IPFixture.randomIPv4,
            )

    val multipleNetworkSwitch: Switch
        get() =
            Switch(
                listOf(
                    NetworkFixture.validNetworkIPv4,
                    NetworkFixture.validNetworkIPv6,
                    NetworkFixture.randomValidNetworkIpv4,
                    NetworkFixture.randomValidNetworkIpv4,
                    NetworkFixture.randomValidNetworkIpv6,
                    NetworkFixture.randomValidNetworkIpv6,
                ),
                SwitchType.LAYER3,
                SwitchIdFixture.randomId,
                IPFixture.randomIPv4,
            )

    val overMaximumAllowedNetworksSwitch: Switch
        get() =
            Switch(
                listOf(
                    NetworkFixture.validNetworkIPv4,
                    NetworkFixture.validNetworkIPv6,
                    NetworkFixture.randomValidNetworkIpv4,
                    NetworkFixture.randomValidNetworkIpv4,
                    NetworkFixture.randomValidNetworkIpv6,
                    NetworkFixture.randomValidNetworkIpv6,
                    NetworkFixture.randomValidNetworkIpv6,
                ),
                SwitchType.LAYER3,
                SwitchIdFixture.randomId,
                IPFixture.randomIPv4,
            )
}
