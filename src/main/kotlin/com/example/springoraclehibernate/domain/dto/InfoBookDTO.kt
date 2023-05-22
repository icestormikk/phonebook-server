package com.example.springoraclehibernate.domain.dto

import java.math.BigInteger

/**
 * @see com.example.springoraclehibernate.domain.InfoBook
 */
data class InfoBookDTO(
    var phone: String,
    var personID: BigInteger,
    var categoryID: BigInteger,
    var addressID: BigInteger,
    var id: BigInteger? = null
)