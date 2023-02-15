package com.ncaoduc.UpAndDownFile.controller;

import com.ncaoduc.UpAndDownFile.service.IStorageService;
import com.ncaoduc.UpAndDownFile.service.ImageStorageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/fileUpLoad")
public class FileUploadController {

    @Autowired
    private ImageStorageServiceImpl service;

    @PostMapping("")
    public ResponseEntity<?> uploadFile(@RequestParam("file")MultipartFile file){
        try {
            String generatedFileName = service.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(generatedFileName);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(exception.getMessage());
        }
    }

    // /files/c05dc0897aab4b51aa43cad601283dbc.jpeg
    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName){

        try{
            byte[] bytes = service.readFileContent(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        } catch (Exception exception){
            return ResponseEntity.noContent().build();
        }

    }

    @GetMapping("")
    public ResponseEntity<?> getUploadedFiles(){

        try{
            List<String> urls = service.loadAll()
                    .map(path -> {
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
                        return urlPath;
                    }).collect(Collectors.toList());
            return ResponseEntity.ok().body(urls);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(e.getMessage());
        }

    }

}
