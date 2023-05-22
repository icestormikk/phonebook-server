package com.example.springoraclehibernate.domain.dto

import java.math.BigInteger

/**
 * @see com.example.springoraclehibernate.domain.Person
 */
data class PersonDTO(
    var id: BigInteger? = null,
    val name: String,
    val surname: String,
    val patronymic: String? = null,
    var email: String? = null,
    var isqId: BigInteger? = null,
)