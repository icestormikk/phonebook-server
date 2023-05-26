package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.address.Address
import com.example.springoraclehibernate.domain.address.interfaces.AddressWithTitlesType
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface AddressRepository : CrudRepository<Address, BigInteger> {
    @Query(
        value = "SELECT" +
                "    AD.ID as ID, C2.TITLE AS Country, C3.TITLE AS City, S.TITLE AS Street," +
                "    AD.HOUSENUMBER AS houseNumber, AD.FLATNUMBER AS flatNumber " +
                "FROM ADDRESS AD\n" +
                "    JOIN COUNTRY C2 on C2.ID = AD.COUNTRY_ID" +
                "    JOIN CITY C3 on C3.ID = AD.CITY_ID" +
                "    JOIN STREET S on S.ID = AD.STREET_ID",
        nativeQuery = true
    )
    fun findAllWithTitles() : List<AddressWithTitlesType>
}
