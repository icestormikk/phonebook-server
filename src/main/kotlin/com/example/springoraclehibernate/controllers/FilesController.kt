package com.example.springoraclehibernate.controllers

import com.example.springoraclehibernate.domain.FileInfo
import com.example.springoraclehibernate.services.implementations.FilesStorageServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder

@RestController
@CrossOrigin
@RequestMapping("/files")
class FilesController(
    @Autowired
    private val filesStorageServiceImpl: FilesStorageServiceImpl
) {
    @GetMapping
    fun getAllFiles(): ResponseEntity<List<FileInfo>> {
        val allFilesInfo = filesStorageServiceImpl
            .fetchAll()

        println(allFilesInfo.map { it.fileName })

        val res = allFilesInfo.map {
                val filename = it.fileName.toString()
                val url = MvcUriComponentsBuilder
                    .fromMethodName(
                        FilesController::class.java,
                        "getFile",
                        it.fileName.toString()
                    )
                    .build()
                    .toString()
                FileInfo(url, filename)
            }

        return ResponseEntity(res, HttpStatus.OK)
    }

    @GetMapping("/filename")
    fun getFile(@RequestParam value: String): ResponseEntity<Resource> {
        val file = filesStorageServiceImpl.load(value)
        return ResponseEntity
            .ok()
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"${ file.filename }\""
            )
            .body(file)
    }

    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<HttpStatus> {
        return try {
            filesStorageServiceImpl.save(file)
            ResponseEntity(HttpStatus.OK)
        } catch (ex: IllegalStateException) {
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}