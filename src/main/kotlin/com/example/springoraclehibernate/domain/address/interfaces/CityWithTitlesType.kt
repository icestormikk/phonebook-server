package com.example.springoraclehibernate.domain.address.interfaces

import java.math.BigInteger

interface CityWithTitlesType {
    fun getId() : BigInteger
    fun getTitle() : String
    fun getCountryTitle() : String
}
