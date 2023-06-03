package com.example.springoraclehibernate.domain

import com.example.springoraclehibernate.domain.address.Address
import jakarta.persistence.Basic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigInteger

/**
 * The category to which people from the phone book belong
 * @property id the unique identifier of the entity
 * @property title name of the category
 */
@Entity
@Table(name = "CATEGORY", schema = "APPUSER")
data class Category(
    @get:Id
    @get:Column(name = "ID", unique = true, nullable = false, updatable = false)
    @get:GeneratedValue(strategy= GenerationType.IDENTITY)
    var id: BigInteger? = null,

    @get:Basic
    @get:Column(name = "TITLE", nullable = false)
    var title: String? = null,
)

