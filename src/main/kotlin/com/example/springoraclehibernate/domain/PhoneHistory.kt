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

