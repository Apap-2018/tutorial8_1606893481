package com.apap.tutorial6.model;

import com.apap.tutorial6.security.ValidPassword;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by esak on 11/7/2018.
 */

@Entity
@Table(name = "user_role")
public class UserRoleModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "username", nullable = false)
    private String username;

    @NotNull
    @Lob
    @ValidPassword
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Size(max = 50)
    @Column(name = "role", nullable = false)
    private String role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
