package com.example.springoraclehibernate.domain

import jakarta.persistence.Basic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigInteger


@Entity
@Table(name = "PHONE_TYPE", schema = "APPUSER")
data class PhoneType(
    @get:Id
    @get:Column(name = "ID", nullable = false)
    @get:GeneratedValue(strategy= GenerationType.IDENTITY)
    var id: BigInteger? = null,

    @get:Basic
    @get:Column(name = "TITLE", nullable = false)
    var title: String? = null,
)

