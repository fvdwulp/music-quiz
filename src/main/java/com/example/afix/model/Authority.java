package com.example.afix.model;

import jakarta.persistence.*;
@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @Column(name = "username")
    private String username;

    @OneToOne
    @MapsId
    @JoinColumn(name = "username")
    private User user;

    @Column(nullable = false)
    private String authority;

    public Authority() {}

    public Authority(User user, String authority) {
        this.user = user;
        this.username = user.getUsername();
        this.authority = authority;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getAuthority() { return authority; }
    public void setAuthority(String authority) { this.authority = authority; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
