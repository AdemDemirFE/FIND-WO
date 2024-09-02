package com.find_wo.backend.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.find_wo.backend.user.validation.UniqueEmail;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
public class User {
    
    @Id
    @GeneratedValue
    long id;

    @NotBlank(message = "{find_wo.constraint.username.notblank}")
    @Size(min = 4, max=255)
    String username;

    @NotBlank
    @Email
    @UniqueEmail
    String email;

    @Size(min = 8, max=255)
    @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
    String password;

    // @JsonIgnore
    boolean active = false;

    // @JsonIgnore
    String activationToken;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getActivationToken() {
        return activationToken;
    }

    public void setActivationToken(String activationToken) {
        this.activationToken = activationToken;
    }
}
