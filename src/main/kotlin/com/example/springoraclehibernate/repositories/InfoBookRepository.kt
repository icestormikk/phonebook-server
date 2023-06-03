package com.example.springoraclehibernate.repositories

import com.example.springoraclehibernate.domain.InfoBook
import com.example.springoraclehibernate.domain.address.interfaces.InfoBookWithTitlesType
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.math.BigInteger

@Repository
interface InfoBookRepository : CrudRepository<InfoBook, BigInteger> {
    fun getAllByPhoneNumber(phoneNumber: String) : List<InfoBook>

    @Query(
        value = "SELECT" +
                "    INFOBOOK.ID AS ID," +
                "    PHONENUMBER as phoneNumber," +
                "    P.SURNAME || ' ' || P.NAME || ' ' || P.PATRONYMIC AS Person," +
                "    P2.TITLE as phoneType " +
                "FROM INFOBOOK" +
                "    JOIN PERSON P on P.ID = INFOBOOK.PERSON_ID" +
                "    JOIN PHONE_TYPE P2 on P2.ID = INFOBOOK.PHONE_TYPE_ID",
        nativeQuery = true
    )
    fun findAllWithTitles() : List<InfoBookWithTitlesType>

    @Query(
        value = "SELECT" +
                "    INFOBOOK.ID," +
                "    INFOBOOK.PERSON_ID," +
                "    INFOBOOK.PHONENUMBER," +
                "    INFOBOOK.PHONE_TYPE_ID " +
                "FROM INFOBOOK" +
                "    WHERE PERSON_ID IN (" +
                "        SELECT ID FROM PERSON" +
                "        WHERE (NAME LIKE :personName OR SURNAME LIKE :personSurname OR PATRONYMIC LIKE :personPatronymic)" +
                "            AND (CATEGORY_ID = :categoryId)" +
                "    )",
        nativeQuery = true
    )
    fun findAllByInitials(
        @Param("personName") name: String?,
        @Param("personSurname") surname: String?,
        @Param("personPatronymic") patronymic: String?,
        @Param("categoryId") categoryId: BigInteger
    ) : List<InfoBook>
}