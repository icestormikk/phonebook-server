package com.example.springoraclehibernate.domain.dto

import java.math.BigInteger

/**
 * @see com.example.springoraclehibernate.domain.address.City
 */
data class CityDTO(
    val title: String,
    val countryID: BigInteger
)