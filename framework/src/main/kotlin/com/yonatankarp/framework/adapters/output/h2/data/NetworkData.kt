package com.yonatankarp.framework.adapters.output.h2.data

import jakarta.persistence.AttributeOverride
import jakarta.persistence.AttributeOverrides
import jakarta.persistence.Column
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Table
import java.io.Serializable
import java.util.UUID

@Entity
@Table(name = "networks")
@MappedSuperclass
open class NetworkData(
    @Id
    @Column(name = "network_id")
    var id: Int = 0,
    @Column(name = "switch_id")
    var switchId: UUID,
    @AttributeOverrides(
        AttributeOverride(
            name = "address",
            column = Column(name = "network_address"),
        ),
        AttributeOverride(
            name = "protocol",
            column = Column(name = "network_protocol"),
        ),
    )
    @Embedded
    var ip: IPData,
    @Column(
        name = "network_name",
    )
    var name: String,
    @Column(name = "network_cidr")
    var cidr: Int,
) : Serializable
