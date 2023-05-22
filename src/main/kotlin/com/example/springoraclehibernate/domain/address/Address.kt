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
import jakarta.persistence.UniqueConstraint
import java.math.BigInteger

/**
 * An entity that stores complete address information
 * @property id the unique identifier of the entity
 * @property countryID id of the country that the address in this entity points to
 * @property streetID id of the street located in the city with the id specified in [cityID]
 * @property cityID id of the city located in the country with the id specified in [countryID]
 * @property houseNumber the number of the house located on the street with the id specified in the [streetID]
 * @property flatNumber the number of the flat located on the house with the number specified in the [houseNumber]
 * @property refCountry link to a [Country] with an id equal to [countryID]
 * @property refStreet link to a [Street] with an id equal to [streetID]
 * @property refCity link to a [City] with an id equal to [cityID]
 */
@Entity
@Table(
    name = "ADDRESS",
    schema = "APPUSER",
    uniqueConstraints = [UniqueConstraint(columnNames = ["COUNTRY_ID", "STREET_ID", "CITY_ID", "houseNumber", "flatNumber"])]
)
data class Address(
    @get:Id
    @get:Column(name = "ID", unique = true, nullable = false, updatable = false)
    @get:GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: BigInteger? = null,

    @get:Basic
    @get:Column(name = "COUNTRY_ID", nullable = false, insertable = false, updatable = false)
    var countryID: BigInteger? = null,

    @get:Basic
    @get:Column(name = "STREET_ID", nullable = false, insertable = false, updatable = false)
    var streetID: BigInteger? = null,

    @get:Basic
    @get:Column(name = "CITY_ID", nullable = false, insertable = false, updatable = false)
    var cityID: BigInteger? = null,

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
)
