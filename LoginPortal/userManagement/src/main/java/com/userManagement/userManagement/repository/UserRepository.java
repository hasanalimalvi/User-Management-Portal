package com.userManagement.userManagement.repository;

import com.userManagement.userManagement.entity.User;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
            "(:username is null or u.username LIKE %:username%) AND " +
            "(:email is null or u.email LIKE %:email%) AND " +
            "(:firstName is null or u.firstName LIKE %:firstName%) AND " +
            "(:lastName is null or u.lastName LIKE %:lastName%) AND " +
            "(:profession is null or u.profession LIKE %:profession%)")
    List<User> customQuery(@Param("username") String username,
                           @Param("email") String email,
                           @Param("firstName") String firstName,
                           @Param("lastName") String lastName,
                           @Param("profession") String profession);

    boolean existsByUsernameAndIdNot(String username, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
