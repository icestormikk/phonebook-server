package com.example.springoraclehibernate.domain.address.interfaces

import java.math.BigInteger

/**
 * Auxiliary interface for database and server communication,
 * contains more detailed information about the City class
 */
interface CityWithTitlesType {
    fun getId() : BigInteger
    fun getTitle() : String
    fun getCountryTitle() : String
}
