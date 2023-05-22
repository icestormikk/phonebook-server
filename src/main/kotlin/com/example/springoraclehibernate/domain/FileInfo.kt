package com.example.springoraclehibernate.domain

/**
 * Brief information about the file available on the server
 * @property url the address of the file in the server file system
 * @property name file name
 */
data class FileInfo(
    val url: String,
    val name: String
)
