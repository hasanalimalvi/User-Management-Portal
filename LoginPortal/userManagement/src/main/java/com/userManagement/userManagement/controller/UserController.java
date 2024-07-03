package com.userManagement.userManagement.controller;


import org.springframework.http.ResponseEntity;
import com.userManagement.userManagement.entity.User;
import com.userManagement.userManagement.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/userManagement")
public class UserController {


    @Autowired
    private UpdateService updateService;

    @PostMapping("/signup/save")
    public User saveUser(@RequestBody User user){
        return updateService.saveUser(user);
    }

    @GetMapping("/currentUser")
    public ResponseEntity<User> getCurrentUser(@RequestParam String email) {
        User user = updateService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser(@RequestParam Long id) {
        User user = updateService.findById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = updateService.findAll();
        return ResponseEntity.ok(users);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User new_user){
        User user = updateService.findById(id);
        return updateService.updateUser(user, new_user);
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id){
        if(!updateService.existById(id)){
            return "User not found";
        }
        User user = updateService.findById(id);
        return updateService.deleteUser(user);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam(required = false) String username,
                                                  @RequestParam(required = false) String email,
                                                  @RequestParam(required = false) String firstName,
                                                  @RequestParam(required = false) String lastName,
                                                  @RequestParam(required = false) String profession) {
        List<User> users = updateService.searchUsers(username, email, firstName, lastName, profession);
        return ResponseEntity.ok(users);
    }
}
