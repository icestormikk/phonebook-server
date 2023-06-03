package com.example.springoraclehibernate.domain.address.interfaces

import java.math.BigInteger

/**
 * An additional interface used to display more detailed information about a person
 */
interface PersonWithTitles {
    fun getId() : BigInteger
    fun getAvatar() : String
    fun getEmail() : String
    fun getIsqId() : String
    fun getName() : String
    fun getSurname() : String
    fun getPatronymic() : String
    fun getCategory() : String
    fun getAddress() : String
}