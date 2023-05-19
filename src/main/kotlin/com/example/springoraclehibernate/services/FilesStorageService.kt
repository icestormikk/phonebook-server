package com.example.springoraclehibernate.services

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path

interface FilesStorageService {
    fun initiate()
    fun save(file: MultipartFile, destFolder: String = ""): String
    fun load(filename: String) : Resource
    fun delete(filename: String)
    fun fetchAll() : List<Path>
    fun deleteAll()
}