package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.InfoBook
import com.example.springoraclehibernate.domain.address.interfaces.InfoBookWithTitlesType
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface InfoBookRepository : CrudRepository<InfoBook, BigInteger> {
    fun getAllByPhoneNumber(phoneNumber: String) : List<InfoBook>
    @Query(
        value = "SELECT" +
                "    PHONENUMBER as phone," +
                "    P.SURNAME || ' ' || P.NAME || ' ' || P.PATRONYMIC AS Person," +
                "    C2.TITLE as category," +
                "    C4.TITLE || ', ' || C3.TITLE || ', ул.' || S.TITLE || ', д.' || A2.HOUSENUMBER || nvl2(A2.FLATNUMBER, ', кв. ' || A2.FLATNUMBER, '') AS Address " +
                "FROM INFOBOOK" +
                "    JOIN PERSON P on P.ID = INFOBOOK.PERSON_ID" +
                "    JOIN CATEGORY C2 on C2.ID = INFOBOOK.CATEGORY_ID" +
                "    JOIN ADDRESS A2 on INFOBOOK.ADDRESS_ID = A2.ID" +
                "    JOIN CITY C3 on C3.ID = A2.CITY_ID" +
                "    JOIN COUNTRY C4 on C4.ID = A2.COUNTRY_ID" +
                "    JOIN STREET S on S.ID = A2.STREET_ID",
        nativeQuery = true
    )
    fun findAllWithTitles() : List<InfoBookWithTitlesType>
}