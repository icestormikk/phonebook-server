package com.example.springoraclehibernate.domain.address.interfaces

import java.math.BigInteger

interface StreetWithTitlesType {
    fun getId() : BigInteger
    fun getTitle() : String
    fun getCityTitle() : String
}