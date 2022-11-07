package com.kudiukin.homework10.service;

import com.kudiukin.homework10.model.Person;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendRegistrationEmail(Person person) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(person.getEmail());
            helper.setSubject("Registration in online shop");
            helper.setText("<h3>Dear, " + person.getFirstName() + "!</h3><br>" +
                    "<h3>your registration in our online shop is successfully.</h3><br>" +
                    "<i><h4>You can start shopping via below link:</h4></i> " +
                    "<a href=\"http://localhost:8080/login\" target=\"_blank\">Log in</a>", true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
