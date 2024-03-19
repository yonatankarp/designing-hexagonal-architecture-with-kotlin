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
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToOne
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.eclipse.persistence.annotations.Convert
import java.io.Serializable
import java.util.UUID

@Entity
@Table(name = "routers")
@MappedSuperclass
open class RouterData(
    @Id
    @Column(name = "router_id", columnDefinition = "uuid", updatable = false)
    @Convert("uuidConverter")
    var routerId: UUID? = null,
    @Column(name = "router_parent_core_id")
    @Convert("uuidConverter")
    var routerParentCoreId: UUID? = null,
    @Embedded
    @Enumerated(EnumType.STRING)
    @Column(name = "router_vendor")
    var routerVendor: VendorData? = null,
    @Embedded
    @Enumerated(EnumType.STRING)
    @Column(name = "router_model")
    var routerModel: ModelData? = null,
    @Embedded
    @AttributeOverrides(
        AttributeOverride(
            name = "address",
            column = Column(name = "router_ip_address"),
        ),
        AttributeOverride(
            name = "protocol",
            column = Column(name = "router_ip_protocol"),
        ),
    )
    val ip: IPData? = null,
    @ManyToOne
    @JoinColumn(name = "location_id")
    val routerLocation: LocationData? = null,
    @Embedded
    @Enumerated(EnumType.STRING)
    @Column(name = "router_type")
    var routerType: RouterTypeData? = null,
    @OneToMany
    @JoinColumn(
        table = "switches",
        name = "router_id",
        referencedColumnName = "router_id",
    )
    val switches: List<SwitchData>? = null,
    @OneToMany
    @JoinTable(
        name = "routers",
        joinColumns = [JoinColumn(name = "router_parent_core_id")],
        inverseJoinColumns = [JoinColumn(name = "router_id")],
    )
    val routers: List<RouterData>? = null,
) : Serializable
