package com.example.springoraclehibernate.services

import com.example.springoraclehibernate.domain.Person
import com.example.springoraclehibernate.domain.PhoneHistory

interface PhoneHistoryService {
    fun getHistory() : List<PhoneHistory>
    fun getHistoryById(id: Long) : PhoneHistory?
    fun getHistoryByPersonID(personID: Long) : PhoneHistory?
    fun getPersonByOldPhone(oldPhone: String) : List<Person>
}