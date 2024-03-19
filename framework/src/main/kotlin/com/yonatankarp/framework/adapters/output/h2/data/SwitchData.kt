package com.yonatankarp.framework.adapters.output.h2.data

import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.OneToMany
import jakarta.persistence.SecondaryTable
import jakarta.persistence.Table
import org.eclipse.persistence.annotations.Convert
import java.io.Serializable
import java.util.UUID

@Entity
@Table(name = "switches")
@SecondaryTable(name = "networks")
@MappedSuperclass
open class SwitchData(
    @Id
    @Column(name = "switch_id", columnDefinition = "uuid", updatable = false)
    @Convert("uuidConverter")
    var switchId: UUID? = null,
    @Column(name = "router_id")
    @Convert("uuidConverter")
    var routerId: UUID? = null,
    @Enumerated(EnumType.STRING)
    @Embedded
    @Column(name = "switch_type")
    var switchType: SwitchTypeData? = null,
    @OneToMany
    @JoinColumn(
        table = "networks",
        name = "switch_id",
        referencedColumnName = "switch_id",
    )
    val networks: List<NetworkData>? = null,
    @Embedded
    @AttributeOverrides(
        AttributeOverride(
            name = "address",
            column = Column(name = "switch_ip_address"),
        ),
        AttributeOverride(
            name = "protocol",
            column = Column(name = "switch_ip_protocol"),
        ),
    )
    val ip: IPData? = null,
) : Serializable
