package com.userManagement.userManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import org.hibernate.annotations.DynamicUpdate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "users")  // Matches the table name in SQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")  // Matches the column name in SQL
    private Long id;

    @Column(name = "username", nullable = false)  // Matches the column name and constraints in SQL
    private String username;

    @Column(name = "email", nullable = false)  // Matches the column name and constraints in SQL
    private String email;

    @Column(name = "password", nullable = false)  // Matches the column name and constraints in SQL
    private String password;

    @Column(name = "firstName")  // Matches the column name in SQL
    private String firstName;

    @Column(name = "lastName")  // Matches the column name in SQL
    private String lastName;

    @Column(name = "contactNo", length = 20)  // Matches the column name and length in SQL
    private String contactNo;

    @Column(name = "address")  // Matches the column name in SQL
    private String address;

    @Column(name = "profession")  // Matches the column name in SQL
    private String profession;
}
