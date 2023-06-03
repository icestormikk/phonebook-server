package com.example.springoraclehibernate.domain.address.interfaces

import java.math.BigInteger

/**
 * Auxiliary interface for database and server communication,
 * contains more detailed information about the [InfoBook] class
 * @see com.example.springoraclehibernate.domain.InfoBook
 */
interface InfoBookWithTitlesType {
    fun getId() : BigInteger
    fun getPhoneNumber(): String
    fun getPerson() : String
    fun getPhoneType(): String?
}