package com.example.springoraclehibernate.services

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path

interface FilesStorageService {
    fun initiate()
    fun save(file: MultipartFile): String
    fun load(filename: String) : Resource
    fun fetchAll() : List<Path>
    fun deleteAll()
}