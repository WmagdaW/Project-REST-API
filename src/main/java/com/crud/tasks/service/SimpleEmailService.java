package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService implements mailMessage {

    @Autowired
    private MailCreatorService mailCreatorService;

    private final JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        log.info("Starting email preparation...");
        try {
            MimeMessagePreparator mimeMailMessage = createMimeMessage(mail);
            javaMailSender.send(mimeMailMessage);
            log.info("Email has been sent.");
        } catch (MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
        };
    }
        private SimpleMailMessage createMailMessage ( final Mail mail){
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(mail.getMailTo());
            mailMessage.setFrom(mail.getMailFrom());
            mailMessage.setSubject(mail.getSubject());
            mailMessage.setText(mail.getMessage());
            if (mail.getToCc() != null) {
                mailMessage.setCc(mail.getToCc());    // 19.3 zadanie
            }

            return mailMessage;
        }

}


//    private SimpleMailMessage createMailMessage(final Mail mail) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(mail.getMailTo());
//        messageHelper.setSubject(mail.getSubject());
//        mailMessage.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()));
//        return mailMessage;
//    }

