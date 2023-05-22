package com.example.springoraclehibernate.domain.address.interfaces

import java.math.BigInteger

/**
 * Auxiliary interface for database and server communication,
 * contains more detailed information about the Street class
 */
interface StreetWithTitlesType {
    fun getId() : BigInteger
    fun getTitle() : String
    fun getCityTitle() : String
}