package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.InfoBook
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface InfoBookRepository : CrudRepository<InfoBook, BigInteger> {
    fun getAllByPhoneNumber(phoneNumber: String) : List<InfoBook>
    fun getAllByEmail(email: String) : List<InfoBook>
    fun getAllByISQID(isqId: BigInteger) : List<InfoBook>
}