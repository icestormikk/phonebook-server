package com.example.springoraclehibernate.domain.dto

import java.math.BigInteger

data class InfoBookDTO(
    var phone: String,
    var personID: BigInteger,
    var categoryID: BigInteger,
    var addressID: BigInteger,
)