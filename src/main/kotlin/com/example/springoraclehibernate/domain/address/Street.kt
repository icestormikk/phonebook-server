package com.example.springoraclehibernate.domain.address

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Basic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.math.BigInteger

/**
 * An entity containing information about a street in a city
 * @property id the unique identifier of the entity
 * @property title name of the country
 * @property cityID id of the [City] where the street is located
 * @property refAddress references to [Address] entities that refer to this entity
 */
@Entity
@Table(name = "STREET", schema = "APPUSER")
data class Street(
    @get:Id
    @get:Column(name = "ID", unique = true, nullable = false, updatable = false)
    @get:GeneratedValue(strategy= GenerationType.IDENTITY)
    var id: BigInteger? = null,

    @get:Basic
    @get:Column(name = "TITLE", nullable = false)
    var title: String? = null,

    @get:Basic
    @get:Column(name = "CITY_ID", nullable = false)
    var cityID: BigInteger? = null,

    @JsonIgnore
    @get:OneToMany(mappedBy = "refStreet")
    var refAddress: List<Address>? = null
)

