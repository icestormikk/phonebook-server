package com.example.springoraclehibernate.domain.address.interfaces

import java.math.BigInteger

/**
 * Auxiliary interface for database and server communication,
 * contains more detailed information about the InfoBook class
 */
interface InfoBookWithTitlesType {
    fun getId() : BigInteger
    fun getPhone(): String
    fun getPerson(): String?
    fun getCategory(): String?
    fun getAddress(): String?
}