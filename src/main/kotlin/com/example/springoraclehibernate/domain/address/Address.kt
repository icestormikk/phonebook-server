package com.example.springoraclehibernate.domain.address

import jakarta.persistence.Basic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "ADDRESS", schema = "APPUSER")
open class Address(
    @get:Id
    @get:Column(name = "ID", unique = true, nullable = false, updatable = false)
    @get:GeneratedValue(strategy=GenerationType.AUTO)
    var id: java.math.BigInteger? = null,

    @get:Basic
    @get:Column(name = "COUNTRY_ID", nullable = false, insertable = false, updatable = false)
    var countryID: java.math.BigInteger? = null,

    @get:Basic
    @get:Column(name = "STREET_ID", nullable = false, insertable = false, updatable = false)
    var streetID: java.math.BigInteger? = null,

    @get:Basic
    @get:Column(name = "CITY_ID", nullable = false, insertable = false, updatable = false)
    var cityID: java.math.BigInteger? = null,

    @get:Basic
    @get:Column(name = "HOUSENUMBER", nullable = true)
    var houseNumber: Long? = null,

    @get:Basic
    @get:Column(name = "FLATNUMBER", nullable = true)
    var flatNumber: Long? = null,

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "COUNTRY_ID", referencedColumnName = "ID")
    var refCountry: Country? = null,

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "STREET_ID", referencedColumnName = "ID")
    var refStreet: Street? = null,

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "CITY_ID", referencedColumnName = "ID")
    var refCity: City? = null,
) {
    fun toWithTitles(): AddressWithTitles {
        return AddressWithTitles(
            id = id,
            countryTitle = refCountry?.title,
            streetTitle = refStreet?.title,
            cityTitle = refCity?.title,
            houseNumber = houseNumber,
            flatNumber = flatNumber,
            refCountry = refCountry,
            refStreet = refStreet,
            refCity = refCity,
        )
    }
}
