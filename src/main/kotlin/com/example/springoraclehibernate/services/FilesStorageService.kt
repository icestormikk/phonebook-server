package com.example.springoraclehibernate.services

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.stream.Stream

interface FilesStorageService {
    fun initiate()
    fun save(file: MultipartFile)
    fun load(filename: String) : Resource
    fun fetchAll() : List<Path>
    fun deleteAll()
}