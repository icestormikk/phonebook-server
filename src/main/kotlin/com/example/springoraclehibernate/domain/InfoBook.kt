package com.example.springoraclehibernate.domain

import com.example.springoraclehibernate.domain.address.Address
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
import java.math.BigInteger

@Entity
@Table(name = "INFOBOOK", schema = "APPUSER")
data class InfoBook(
    @get:Id
    @get:Column(name = "ID", unique = true, nullable = false, updatable = false)
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: BigInteger? = null,

    @get:Basic
    @get:Column(name = "PHONENUMBER", nullable = false)
    var phoneNumber: String? = null,

    @get:Basic
    @get:Column(name = "PERSON_ID", nullable = false, insertable = false, updatable = false)
    var personID: BigInteger? = null,

    @get:Basic
    @get:Column(name = "CATEGORY_ID", nullable = false, insertable = false, updatable = false)
    var categoryID: BigInteger? = null,

    @get:Basic
    @get:Column(name = "ADDRESS_ID", nullable = false, insertable = false, updatable = false)
    var addressID: BigInteger? = null,

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    var refPerson: Person? = null,

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    var refCategory: Category? = null,

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    var refAddress: Address? = null,
)
