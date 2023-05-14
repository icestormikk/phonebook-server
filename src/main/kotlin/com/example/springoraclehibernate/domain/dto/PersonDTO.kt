package com.example.springoraclehibernate.domain.dto

import java.math.BigInteger

data class PersonDTO(
    val name: String,
    val surname: String,
    val patronymic: String? = null,
    var email: String? = null,
    var ISQID: BigInteger? = null,
)