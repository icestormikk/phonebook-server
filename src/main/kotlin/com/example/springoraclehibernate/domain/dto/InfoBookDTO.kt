package com.example.springoraclehibernate.domain.dto

import java.math.BigInteger

data class InfoBookDTO(
    var phone: String,
    var email: String? = null,
    var ISQID: BigInteger? = null,
    var personID: BigInteger,
    var categoryID: BigInteger,
    var addressID: BigInteger,
)