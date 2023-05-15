package co.develhope.fileuploadanddownload.services;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${directoryFile}")
    String directory;
    public String upload(MultipartFile file) throws Exception {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String newName = UUID.randomUUID().toString();
        String completeName = newName + "." + extension;
        File fileFolder = new File(directory);
        if (!fileFolder.exists()) throw new Exception("Il file non esiste!");
        if (!fileFolder.isDirectory()) throw new Exception("La directory non esiste!");
        File finalDestination = new File(directory + "\\" + completeName);
        if (finalDestination.exists()) throw new Exception("Il file esiste già");
        file.transferTo(finalDestination);
        return completeName;
    }
    public byte[] download(String fileName) throws Exception {
        File fileRepository = new File(directory + "\\" + fileName);
        if(!fileRepository.exists()) throw new Exception("Il file non esiste");
        return IOUtils.toByteArray(new FileInputStream(fileRepository));
    }
}
