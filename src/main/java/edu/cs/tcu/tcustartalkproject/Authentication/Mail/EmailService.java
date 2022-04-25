package edu.cs.tcu.tcustartalkproject.Authentication.Mail;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void sendTextEmail(String response, String email);
}