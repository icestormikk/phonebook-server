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
@Table(name = "PERSON", schema = "APPUSER")
data class Person(
    @get:Id
    @get:Column(name = "ID", unique = true, nullable = false, updatable = false)
    @get:GeneratedValue(strategy=GenerationType.AUTO)
    var id: Int? = null,

    @get:Basic
    @get:Column(name = "NAME", nullable = false)
    var name: String? = null,

    @get:Basic
    @get:Column(name = "SURNAME", nullable = false)
    var surname: String? = null,

    @get:Basic
    @get:Column(name = "PATRONYMIC", nullable = true)
    var patronymic: String? = null,

    @JsonIgnore
    @get:OneToMany(mappedBy = "refPerson")
    var refInfoBook: List<InfoBook>? = null
)