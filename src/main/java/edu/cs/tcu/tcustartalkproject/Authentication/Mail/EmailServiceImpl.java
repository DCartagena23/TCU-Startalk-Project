package edu.cs.tcu.tcustartalkproject.Authentication.Mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import edu.cs.tcu.tcustartalkproject.Authentication.Mail.EmailService;

import java.time.LocalDate;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory
            .getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;



    @Override
    public void sendTextEmail(String response, String email) {
        logger.info("Simple Email sending start");

        SimpleMailMessage simpleMessage = new SimpleMailMessage();
        simpleMessage.setTo(email);
        simpleMessage.setSubject("STARTALK: Reset Password");
        simpleMessage.setText("Dear " + email + ", Hope you are doing well. Please go to the following link to reset your password  \n\n" + response);
        simpleMessage.setFrom("tcustartalk@gmail.com");

        System.out.println(simpleMessage);
        javaMailSender.send(simpleMessage);

        logger.info("Simple Email sent");

    }
}