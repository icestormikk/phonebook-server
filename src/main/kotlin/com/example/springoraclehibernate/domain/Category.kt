package com.example.springoraclehibernate.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Basic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "CATEGORY", schema = "APPUSER")
data class Category(
    @get:Id
    @get:Column(name = "ID", unique = true, nullable = false, updatable = false)
    @get:GeneratedValue(strategy= GenerationType.AUTO)
    var id: java.math.BigInteger? = null,

    @get:Basic
    @get:Column(name = "TITLE", nullable = false)
    var title: String? = null,

    @JsonIgnore
    @get:OneToMany(mappedBy = "refCategory")
    var refInfoBook: List<InfoBook>? = null
)

