package com.example.springoraclehibernate.services

import com.example.springoraclehibernate.domain.InfoBook
import com.example.springoraclehibernate.domain.dto.InfoBookDTO
import java.math.BigInteger

interface InfoBookService {
    fun getAllInfos(withTitles: Boolean = false) : List<Any>
    fun getInfoById(id: BigInteger) : InfoBook?
    fun getInfosByPhoneNumber(phoneNumber: String) : List<InfoBook>
    fun getAllByInitials(name: String?, surname: String?, patronymic: String?, category: String) : List<InfoBook>
    fun addInfo(infoBookDTO: InfoBookDTO) : InfoBook
    fun updateInfo(infoBookDTO: InfoBookDTO) : InfoBook
    fun removeInfoById(id: BigInteger)
}