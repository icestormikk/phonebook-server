package com.example.springoraclehibernate.domain.dto

import java.math.BigInteger

data class AddressDTO(
    val countryID: BigInteger,
    val streetID: BigInteger,
    val cityID: BigInteger,
    val houseNumber: BigInteger? = null,
    val flatNumber: BigInteger? = null
)