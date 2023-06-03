package com.example.springoraclehibernate.domain.dto

import java.math.BigInteger

/**
 * @see com.example.springoraclehibernate.domain.InfoBook
 */
data class InfoBookDTO(
    var phoneNumber: String,
    var personID: BigInteger,
    var phoneTypeID: BigInteger,
    var id: BigInteger? = null
)