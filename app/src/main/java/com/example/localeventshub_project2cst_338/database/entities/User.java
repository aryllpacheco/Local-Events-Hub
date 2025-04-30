package com.example.localeventshub_project2cst_338.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.localeventshub_project2cst_338.database.LocalEventsDatabase;

import java.util.Objects;

@Entity(tableName = LocalEventsDatabase.USER_TABLE)
public class User {

    @PrimaryKey
    private int id;
    private String username;
    private String password;
    private int zipcode;
    private boolean isAdmin;

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public User(String username, String password, int zipcode) {
        this.username = username;
        this.password = password;
        this.zipcode = zipcode;
        isAdmin = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && zipcode == user.zipcode && isAdmin == user.isAdmin && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, zipcode, isAdmin);
    }
}
