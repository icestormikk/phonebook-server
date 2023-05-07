package com.example.springoraclehibernate.domain.address

import java.math.BigInteger

data class AddressWithTitles(
    var id: BigInteger? = null,
    var countryTitle: String? = null,
    var streetTitle: String? = null,
    var cityTitle: String? = null,
    var houseNumber: Long? = null,
    var flatNumber: Long? = null,
    var refCountry: Country? = null,
    var refStreet: Street? = null,
    var refCity: City? = null,
)