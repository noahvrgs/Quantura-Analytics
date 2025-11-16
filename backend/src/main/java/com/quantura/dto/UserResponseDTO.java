package com.quantura.dto;

public class UserResponseDTO {
    private Long id;
    private String email;

    // ===== << Constructors >> =====
    public RegisterRequestDTO() {}

    public RegisterRequestDTO(Long id, String email){
        this.id = id;
        this.email = email;
    }

    // ===== << Getters >> =====
    public String getEmail() { return email; }
    public String getId() { return id; }

    // ===== << Setters >> =====
    public void setEmail(String email) { this.email = email; }
    public void setId(String id) { this.id = id; } 
}
