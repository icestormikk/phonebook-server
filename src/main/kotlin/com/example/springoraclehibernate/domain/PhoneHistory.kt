package com.example.springoraclehibernate.domain

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
import java.sql.Timestamp

/**
 * An entity created for the storage of particularly important military objects
 * @property id the unique identifier of the entity
 * @property personID id of the [Person] class object for which this record was created
 * @property startDate the beginning of the time of using this phone number
 * @property endDate the end of the time of using this phone number
 * @property phone the current value of the subscriber's phone number
 * @property refPersonEntity = references to [Person] entities that refer to this entity
 */
@Entity
@Table(name = "PHONEHISTORY", schema = "APPUSER")
data class PhoneHistory(
    @get:Id
    @get:Column(name = "ID", nullable = false)
    @get:GeneratedValue(strategy= GenerationType.IDENTITY)
    var id: Long? = null,

    @get:Basic
    @get:Column(name = "PERSON_ID", nullable = false, insertable = false, updatable = false)
    var personID: Long? = null,

    @get:Basic
    @get:Column(name = "START_DATE", nullable = false)
    var startDate: Timestamp? = null,

    @get:Basic
    @get:Column(name = "END_DATE", nullable = true)
    var endDate: Timestamp? = null,

    @get:Basic
    @get:Column(name = "PHONE", nullable = false)
    var phone: String? = null,

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    var refPersonEntity: Person? = null
)

