package com.example.springoraclehibernate.domain.address.interfaces

import java.math.BigInteger

interface AddressWithTitlesType {
    fun getId() : BigInteger
    fun getCountry() : String
    fun getStreet() : String
    fun getCity() : String
    fun getHouseNumber() : Long
    fun getFlatNumber() : Long? = null
}