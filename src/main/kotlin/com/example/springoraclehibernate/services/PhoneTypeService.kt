package com.example.springoraclehibernate.services

import com.example.springoraclehibernate.domain.PhoneType
import java.math.BigInteger

interface PhoneTypeService {
    fun getAllTypes() : List<PhoneType>
    fun getTypeById(id: BigInteger) : PhoneType?
    fun addType(type: PhoneType) : PhoneType
    fun deleteType(id: BigInteger)
    fun updateType(type: PhoneType) : PhoneType
}