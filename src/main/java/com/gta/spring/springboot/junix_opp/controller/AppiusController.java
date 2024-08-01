package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.servise.AppiusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/appius")
@RequiredArgsConstructor
public class AppiusController {

    private final AppiusService excelService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            excelService.saveExcelData(file);
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded and data saved successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload and save data.");
        }
    }
}
