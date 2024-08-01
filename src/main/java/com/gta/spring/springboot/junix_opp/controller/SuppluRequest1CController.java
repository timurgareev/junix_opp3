package com.gta.spring.springboot.junix_opp.controller;

import com.gta.spring.springboot.junix_opp.servise.SupplyRequests1CService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/supplyrequest1c")
@RequiredArgsConstructor
public class SuppluRequest1CController {

    private final SupplyRequests1CService supplyRequests1CService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        try {
            supplyRequests1CService.saveExcelData(file);
            return ResponseEntity.ok("File uploaded and data saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload and process file.");
        }
    }
}
