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

@Entity
@Table(name = "INFOBOOK", schema = "APPUSER")
data class InfoBook(
    @get:Id
    @get:Column(name = "ID", unique = true, nullable = false, updatable = false)
    @get:GeneratedValue(strategy = GenerationType.AUTO)
    var id: java.math.BigInteger? = null,

    @get:Basic
    @get:Column(name = "PHONENUMBER", nullable = false)
    var phoneNumber: String? = null,

    @get:Basic
    @get:Column(name = "EMAIL", nullable = true)
    var email: String? = null,

    @get:Basic
    @get:Column(name = "ISQ_ID", nullable = true)
    var ISQID: java.math.BigInteger? = null,

    @get:Basic
    @get:Column(name = "PERSON_ID", nullable = false, insertable = false, updatable = false)
    var personID: java.math.BigInteger? = null,

    @get:Basic
    @get:Column(name = "CATEGORY_ID", nullable = false, insertable = false, updatable = false)
    var categoryID: java.math.BigInteger? = null,

    @get:Basic
    @get:Column(name = "ADDRESS_ID", nullable = false, insertable = false, updatable = false)
    var addressID: java.math.BigInteger? = null,

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    var refPerson: Person? = null,

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    var refCategory: Category? = null,

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    var refAddress: Address? = null,
) {
    fun toInfoBookWithTitles(): InfoBookWithTitles {
        val initials = "${refPerson?.surname} ${refPerson?.name}" + (
            if (refPerson?.patronymic != null) " ${refPerson?.patronymic}" else ""
        )
        val addressTitles = refAddress?.toWithTitles()
        val address = "${addressTitles?.countryTitle}, г.${addressTitles?.cityTitle}, " +
            "ул.${addressTitles?.streetTitle}, д.${addressTitles?.houseNumber}" + (
            if (addressTitles?.flatNumber != null) ", кв.${addressTitles?.flatNumber}" else ""
        )

        return InfoBookWithTitles(
            id = id,
            phone = phoneNumber,
            email = email,
            ISQID = ISQID,
            person = initials,
            categoryTitle = refCategory?.title,
            address = address,
        )
    }
}

