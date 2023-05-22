package com.example.springoraclehibernate.domain.dto

import java.math.BigInteger

/**
 * @see com.example.springoraclehibernate.domain.address.Address
 */
data class AddressDTO(
    val countryID: BigInteger,
    val streetID: BigInteger,
    val cityID: BigInteger,
    val houseNumber: BigInteger? = null,
    val flatNumber: BigInteger? = null,
    val id: BigInteger? = null
)