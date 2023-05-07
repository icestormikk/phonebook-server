package com.example.springoraclehibernate.services

import com.example.springoraclehibernate.domain.InfoBook
import com.example.springoraclehibernate.domain.dto.InfoBookDTO
import java.math.BigInteger

interface InfoBookService {
    fun getAllInfos() : List<InfoBook>
    fun getInfoById(id: BigInteger) : InfoBook?
    fun getInfosByPhoneNumber(phoneNumber: String) : List<InfoBook>
    fun getInfosByEmail(email: String) : List<InfoBook>
    fun getInfosByISQId(isqId: BigInteger) : List<InfoBook>
    fun addInfo(infoBookDTO: InfoBookDTO) : InfoBook
    fun removeInfoById(id: BigInteger)
}