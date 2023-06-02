package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.domain.Person
import com.example.springoraclehibernate.domain.PhoneHistory
import com.example.springoraclehibernate.repositories.PersonRepository
import com.example.springoraclehibernate.repositories.PhoneHistoryRepository
import com.example.springoraclehibernate.services.PhoneHistoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PhoneHistoryServiceImpl(
    @Autowired
    private val phoneHistoryRepository: PhoneHistoryRepository,
    @Autowired
    private val personRepository: PersonRepository
) : PhoneHistoryService {
    override fun getHistory(): List<PhoneHistory> {
        return phoneHistoryRepository.findAll().toList()
    }

    override fun getHistoryById(id: Long): PhoneHistory? {
        val history = phoneHistoryRepository.findById(id)

        return if (history.isEmpty) {
            null
        } else {
            history.get()
        }
    }

    override fun getHistoryByPersonID(personID: Long): PhoneHistory? {
        val history = phoneHistoryRepository.findByPersonID(personID)

        return if (history.isEmpty) {
            null
        } else {
            history.get()
        }
    }

    override fun getPersonByOldPhone(oldPhone: String): List<Person> {
        val personIDs = phoneHistoryRepository.findPeopleByOldPhone(oldPhone)
        return personRepository.findAllById(personIDs).toList()
    }
}