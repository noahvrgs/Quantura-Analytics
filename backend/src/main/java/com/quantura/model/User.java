package com.quantura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String role;

    public User() {}
    public User(String username, String password, String email, String name, String role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    // ===== GETTERS =====
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public String getRole() { return role; }


    // ===== SETTERS =====
    public String setUsername(String username) { this.username = username; }
    public String setPassword(String password) { this.password = password; }
    public String setEmail(String email) { this.email = email;}
    public String setName(String name) { this.name = name; }
    public String setRole(String role) { this.role = role; }

}
