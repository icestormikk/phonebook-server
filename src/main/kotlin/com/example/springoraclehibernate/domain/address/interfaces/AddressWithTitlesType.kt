package com.example.springoraclehibernate.domain.address.interfaces

interface AddressWithTitlesType {
    fun getCountry() : String
    fun getStreet() : String
    fun getCity() : String
    fun getHouseNumber() : Long
    fun getFlatNumber() : Long? = null
}