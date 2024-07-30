package com.lukegjpotter.restassured.RestAssuredDemos.lotto.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
public class AdminAndRole {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String userName;
    @Column(unique = true)
    private String bearerToken;
    private String role;

    public AdminAndRole() {
    }

    public AdminAndRole(String userName, String bearerToken, String role) {
        this.userName = userName;
        this.bearerToken = bearerToken;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBearerToken() {
        return bearerToken;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminAndRole that = (AdminAndRole) o;
        return Objects.equals(id, that.id) && Objects.equals(userName, that.userName) && Objects.equals(bearerToken, that.bearerToken) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, bearerToken, role);
    }

    @Override
    public String toString() {
        return "AdminAndRoles{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", bearerToken='" + bearerToken + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
