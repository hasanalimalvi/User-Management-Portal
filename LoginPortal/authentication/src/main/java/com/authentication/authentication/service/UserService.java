package com.authentication.authentication.service;

import com.authentication.authentication.DTO.LoginDTO;
import com.authentication.authentication.entity.User;
import com.authentication.authentication.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.io.File;
@Service


public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        // Encode the user's password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public ResponseEntity<?> loginUser(LoginDTO loginDTO){
        String email = loginDTO.getEmail();
        String password = loginDTO.getPassword();
        User user = userRepository.findByEmail(email);
        if(user!=null){
            if(passwordEncoder.matches(password,user.getPassword())){
                return ResponseEntity.ok("Login Success!!");
            }
            else{
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Password Incorrect");
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User doesn't exist");
        }
    }

    public User findByUsernameOrEmail(String username, String email){
        return userRepository.findByUsernameOrEmail(username, email);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleEmail(String toEmail, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("hasanalimalvi10@gmail.com");
            message.setTo(toEmail);
            message.setText(body);
            message.setSubject("Verification Code");
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }

    public boolean verifyOTP(String email, String otp) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getOtp() != null && user.getOtp().equals(otp)) {
            return true;
        }
        return false;
    }
}
