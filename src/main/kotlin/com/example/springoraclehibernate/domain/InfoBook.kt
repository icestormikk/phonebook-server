package com.example.springoraclehibernate.domain

import com.fasterxml.jackson.annotation.JsonIgnore
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

/**
 * The basic unit of the phone book
 * @property id the unique identifier of the entity
 * @property phoneNumber subscriber's phone number in the record
 * @property personID id of the [Person] class object for which this record was created
 * @property refPerson  references to [Person] entities that refer to this entity
 */
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
    @get:Column(name = "PHONE_TYPE_ID", nullable = false, insertable = false, updatable = false)
    var phoneTypeID: BigInteger? = null,

    @JsonIgnore
    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    var refPerson: Person? = null,

    @JsonIgnore
    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "PHONE_TYPE_ID", referencedColumnName = "ID")
    var refPhoneType: PhoneType? = null,
)
