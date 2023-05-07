package com.example.springoraclehibernate.domain.dto

data class PersonDTO(
    val name: String,
    val surname: String,
    val patronymic: String? = null
)