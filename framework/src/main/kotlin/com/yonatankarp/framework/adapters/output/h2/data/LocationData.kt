package com.yonatankarp.framework.adapters.output.h2.data

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Table

@Entity
@Table(name = "location")
@MappedSuperclass
open class LocationData {
    @Id
    @Column(name = "location_id")
    var locationId = 0

    @Column(name = "address")
    var address: String? = null

    @Column(name = "city")
    var city: String? = null

    @Column(name = "state")
    var state: String? = null

    @Column(name = "zipcode")
    var zipcode = 0

    @Column(name = "country")
    var country: String? = null

    @Column(name = "latitude")
    var latitude = 0f

    @Column(name = "longitude")
    var longitude = 0f
}
