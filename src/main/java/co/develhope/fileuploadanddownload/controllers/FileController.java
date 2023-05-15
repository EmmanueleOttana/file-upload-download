package co.develhope.fileuploadanddownload.controllers;

import co.develhope.fileuploadanddownload.services.FileStorageService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileStorageService fileStorageService;

    @PostMapping("/upload")
    public List<String> upload(@RequestParam MultipartFile[] files) throws Exception {
        List<String> fileNames = new ArrayList<>();
        for ( MultipartFile file : files ) {
            String singleUpload = fileStorageService.upload(file);
            fileNames.add(singleUpload);
        }
        return fileNames;
    }
    @GetMapping("/download")
    public byte[] download(@RequestParam String fileName, HttpServletResponse response) throws Exception {
        String extension = FilenameUtils.getExtension(fileName);
        switch (extension) {
            case "jpg", "jpeg" -> response.setContentType(MediaType.IMAGE_JPEG_VALUE);
            case "gif" -> response.setContentType(MediaType.IMAGE_GIF_VALUE);
            case "png" -> response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader("Content-Disposition","attachment; fileName=\""+fileName+"\"");
        return fileStorageService.download(fileName);
    }

}
