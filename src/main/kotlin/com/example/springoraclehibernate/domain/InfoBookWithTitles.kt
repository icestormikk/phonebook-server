package com.example.springoraclehibernate.domain

import java.math.BigInteger

data class InfoBookWithTitles(
    var id: BigInteger? = null,
    var phone: String?,
    var email: String? = null,
    var ISQID: BigInteger? = null,
    var person: String?,
    var categoryTitle: String?,
    var address: String?,
)