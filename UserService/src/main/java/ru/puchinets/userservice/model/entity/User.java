package ru.puchinets.userservice.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users", schema = "user_management")
public class User {
    @Id
    @SequenceGenerator(name = "user_seq", allocationSize = 1, sequenceName = "users_id_seq", schema = "user_management")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;
    @Column(name = "username", unique = true, nullable = false)
    @Email
    private String username;
    @Column(name = "password_hash", nullable = false)
    @NotEmpty
    //@Size(min = 8, max = 128, message = USERNAME_LENGTH_MSG)
    private String passwordHash;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "birth_date")
    @Past
    private LocalDate birthDate;
    private String phone;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
}
