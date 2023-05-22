package com.example.springoraclehibernate.domain.dto

import java.math.BigInteger

/**
 * @see com.example.springoraclehibernate.domain.address.Street
 */
data class StreetDTO(
    val title: String,
    val cityID: BigInteger
)