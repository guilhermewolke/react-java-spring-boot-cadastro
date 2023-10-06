package com.github.FEBackEnd.shared.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MailerConfigDTO {
    private String to;
    private String subject;
    private String template;
}
