package com.github.FEBackEnd.shared.upload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FileUploadOutputDTO {
    private String message;
    private String filename;
}
