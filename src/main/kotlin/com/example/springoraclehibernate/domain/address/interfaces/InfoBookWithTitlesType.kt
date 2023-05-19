package com.example.springoraclehibernate.domain.address.interfaces

import java.math.BigInteger

interface InfoBookWithTitlesType {
    fun getId() : BigInteger
    fun getPhone(): String
    fun getPerson(): String?
    fun getCategory(): String?
    fun getAddress(): String?
}