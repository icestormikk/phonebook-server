package com.example.springoraclehibernate.services

import com.example.springoraclehibernate.domain.PhoneHistory

interface PhoneHistoryService {
    fun getHistory() : List<PhoneHistory>
    fun getHistoryById(id: Long) : PhoneHistory?
    fun getHistoryByPersonID(personID: Long) : PhoneHistory?
}