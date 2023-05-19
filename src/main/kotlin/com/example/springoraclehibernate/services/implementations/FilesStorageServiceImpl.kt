package com.example.springoraclehibernate.services.implementations

import com.example.springoraclehibernate.services.FilesStorageService
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

@Service
class FilesStorageServiceImpl : FilesStorageService {
    private val root = Paths.get("src/main/resources/static/users")

    override fun initiate() {
        try {
            Files.createDirectories(root)
        } catch (ex: IOException) {
            error("Error during storage initialization: ${ex.localizedMessage}")
        }
    }

    override fun save(file: MultipartFile, destFolder: String) : String {
        return try {
            val filename = file.originalFilename.toString()
            val fullPath = "${destFolder}/$filename"
            Files.createDirectories(root.resolve(destFolder))
            Files.copy(file.inputStream, root.resolve(fullPath))
            fullPath
        } catch (ex: Exception) {
            if (ex is FileAlreadyExistsException) {
                error("A file of that name already exists!")
            }

            error("${ex.message}")
        }
    }

    override fun delete(filename: String) {
        try {
            val path = root.resolve(filename)
            Files.deleteIfExists(Paths.get(path.toUri()))
        } catch (ex: Exception) {
            error("Error during file deletion: ${ex.message}")
        }
    }

    override fun load(filename: String): Resource {
        try {
            val file = root.resolve(filename)
            val resource = UrlResource(file.toUri())

            return if (resource.exists() || resource.isReadable) {
                resource
            } else {
                error("Can not read the file!")
            }
        } catch (ex: MalformedURLException) {
            error("Error: ${ex.message}")
        }
    }

    override fun fetchAll(): List<Path> {
        return try {
            Files.walk(this.root, 1)
                .filter { !it.equals(root) }
                .map(this.root::relativize)
                .toList()
        } catch (ex: IOException) {
            error("Can not read the files!")
        }
    }

    override fun deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile())
    }
}