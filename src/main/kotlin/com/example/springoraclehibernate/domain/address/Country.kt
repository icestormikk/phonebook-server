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

/**
 * An entity that stores minimal information about the country
 * @property id the unique identifier of the entity
 * @property title name of the country
 * @property refAddress references to [Address] entities that refer to this entity
 */
@Entity
@Table(name = "COUNTRY", schema = "APPUSER")
data class Country(
    @get:Id
    @get:Column(name = "ID", unique = true, nullable = false, updatable = false)
    @get:GeneratedValue(strategy= GenerationType.IDENTITY)
    var id: java.math.BigInteger? = null,

    @get:Basic
    @get:Column(name = "TITLE", nullable = false)
    var title: String? = null,

    @JsonIgnore
    @get:OneToMany(mappedBy = "refCountry")
    var refAddress: List<Address>? = null
)

