package com.authentication.authentication.controller;

import com.authentication.authentication.DTO.LoginDTO;

import com.authentication.authentication.entity.User;
import com.authentication.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Random;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;



    @PostMapping("/signup/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        // Check if username or email already exists
        User existingUser = userService.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (existingUser != null) {
            // Username or email already exists, return a conflict response
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            // Username and email are unique, save the user
            User savedUser = userService.saveUser(user);
            return ResponseEntity.ok(savedUser);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){

        return userService.loginUser(loginDTO);
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<String> sendOTP(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        User user = userService.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Check email id!!");
        }

        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        String OTP = String.format("%06d", number);
        user.setOtp(OTP); // Assuming your User entity has a field 'otp'
        userService.saveUser(user);
        String body = "OTP: " + OTP;
        userService.sendSimpleEmail(email, body);

        return ResponseEntity.ok("OTP sent successfully");
    }

    @PostMapping("/verifyOTP")
    public ResponseEntity<String> verifyOTP(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String otp = payload.get("otp");

        if (userService.verifyOTP(email, otp)) {
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid OTP");
        }
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> payload){
        String email = payload.get("email");
        String password = payload.get("password");
        User user = userService.findByEmail(email);
        user.setPassword(password);
        userService.saveUser(user);
        return ResponseEntity.ok("Password has been reset successfully!!");
    }





}
