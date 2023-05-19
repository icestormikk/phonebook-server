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

@Entity
@Table(name = "CITY", schema = "APPUSER")
data class City(
    @get:Id
    @get:Column(name = "ID", unique = true, nullable = false, updatable = false)
    @get:GeneratedValue(strategy=GenerationType.IDENTITY)
    var id: BigInteger? = null,

    @get:Basic
    @get:Column(name = "TITLE", nullable = false)
    var title: String? = null,

    @get:Basic
    @get:Column(name = "COUNTRY_ID", nullable = false)
    var countryID: BigInteger? = null,

    @JsonIgnore
    @get:OneToMany(mappedBy = "refCity")
    var refAddress: List<Address>? = null
)
