package com.quantura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    
    // ===== ID Generation =====
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ===== Relationships =====
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserSettings settings;

    // ===== Variables =====
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String role;

    public User() {}

    public User(String username, String passwordHash, String email, String name, String role){
        this.username = username;
        this.password = passwordHash;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    // ===== Getters =====
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getRole() { return role; }


    // ===== Setters =====
    public void setUsername(String username) { this.username = username; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setEmail(String email) { this.email = email;}
    public void setName(String name) { this.name = name; }
    public void setRole(String role) { this.role = role; }

}
