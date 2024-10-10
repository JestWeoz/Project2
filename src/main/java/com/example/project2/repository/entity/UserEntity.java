package com.example.project2.repository.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "fullname")
    private String fullName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<UserRoleEntity> userRolesEntity = new ArrayList<UserRoleEntity>();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<UserRoleEntity> getUserRolesEntity() {
        return userRolesEntity;
    }

    public void setUserRolesEntity(List<UserRoleEntity> userRolesEntity) {
        this.userRolesEntity = userRolesEntity;
    }
}
