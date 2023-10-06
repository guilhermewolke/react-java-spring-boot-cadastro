package com.github.FEBackEnd.shared.upload.controller;

import com.github.FEBackEnd.shared.upload.dto.FileUploadOutputDTO;
import com.github.FEBackEnd.shared.upload.service.FileStorageService;
import com.github.FEBackEnd.shared.upload.service.Storable;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@CrossOrigin("http://localhost:3000")
@RequestMapping("/upload")
public class FileUploadController {

    @Autowired(required = true)
    private Storable storageService;

    @PostMapping
    public ResponseEntity<FileUploadOutputDTO> upload(@RequestParam("file") MultipartFile file) {
        String message = "";

        try {
            storageService.save(file);
            message = "Arquivo '" + file.getOriginalFilename() + "' enviado com sucesso";
            return ResponseEntity.status(HttpStatus.OK).body(new FileUploadOutputDTO(message, file.getName()));
        } catch (Exception e) {
            message = "Não foi possível realizar o upload deste arquivo. Erro: '" + e.getMessage() + "'";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new FileUploadOutputDTO(message, file.getName()));
        }
    }
    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
