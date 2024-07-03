package com.userManagement.userManagement.service;

import com.userManagement.userManagement.entity.User;
import com.userManagement.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UpdateService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        return userRepository.save(user);
    }
    public boolean existById(Long id) {
        return userRepository.existsById(id);
    }



    public ResponseEntity<?> updateUser(User user, User new_user) {


        // Check if the new username already exists
        if (!(user.getUsername().equals(new_user.getUsername())) && userRepository.existsByUsername(new_user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        // Check if the new email already exists
        if (!(user.getEmail().equals(new_user.getEmail())) && userRepository.existsByEmail(new_user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

//        // Encode the password
//        String encodedPassword = passwordEncoder.encode(new_user.getPassword());
//        user.setPassword(encodedPassword);

        // Update user details
        user.setUsername(new_user.getUsername());
        user.setEmail(new_user.getEmail());
        user.setFirstName(new_user.getFirstName());
        user.setLastName(new_user.getLastName());
        user.setContactNo(new_user.getContactNo());
        user.setAddress(new_user.getAddress());
        user.setProfession(new_user.getProfession());

        // Save the updated user
        userRepository.save(user);

        return ResponseEntity.ok("User details updated successfully");
    }



    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public String deleteUser(User user) {
        userRepository.delete(user);
        return "User deleted";
    }

    public List<User> searchUsers(String username, String email, String firstName, String lastName, String profession) {
        return userRepository.customQuery(username, email, firstName, lastName, profession);
    }


    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}

