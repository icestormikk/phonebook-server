package com.example.springoraclehibernate.services

import com.example.springoraclehibernate.domain.PhoneType

interface PhoneTypeService {
    fun getAllTypes() : List<PhoneType>
    fun getTypeById(id: Long) : PhoneType?
    fun addType(type: PhoneType) : PhoneType
    fun deleteType(id: Long)
    fun updateType(type: PhoneType) : PhoneType
}