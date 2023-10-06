package com.github.FEBackEnd.shared.mail.service;

import com.github.FEBackEnd.domain.person.dto.PersonOutputDTO;
import com.github.FEBackEnd.shared.mail.dto.MailerConfigDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class MailService {
    @Autowired
    private final JavaMailSender mailer;

    @Autowired
    private final TemplateEngine templateEngine;

    @Async
    public void sendMail(MailerConfigDTO config) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(config.getTo());
        message.setSubject(config.getSubject());
        //message.setText(config.getBody());

        this.mailer.send(message);
    }

    public void sendHTMLEmail(MailerConfigDTO config, PersonOutputDTO person) {
        MimeMessage mime = mailer.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mime, "UTF-8");
        Context context = new Context();

        context.setVariable("id", Long.toString(person.getId()));
        context.setVariable("name", person.getName());
        context.setVariable("email", person.getEmail());
        context.setVariable("birthDate", person.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        try {
            helper.setTo(config.getTo());
            helper.setSubject(config.getSubject());
            String htmlContent = templateEngine.process(config.getTemplate(), context);
            helper.setText(htmlContent, true);
            mailer.send(mime);
        } catch(MessagingException e) {
            e.printStackTrace();
        }
    }
}
