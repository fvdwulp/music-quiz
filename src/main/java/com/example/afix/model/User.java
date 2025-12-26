package com.example.afix.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private char enabled;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Authority authority;

    public User() {}

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public char getEnabled() { return enabled; }
    public void setEnabled(char enabled) { this.enabled = enabled; }

    public Authority getAuthority() { return authority; }
    public void setAuthority(Authority authority) { this.authority = authority; }
}
