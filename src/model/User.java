/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thisi
 */
public class User {
    private int id;
    private String fullName;
    private String email;
    private String username;
    private String password;
    private String role;

    public User(int id, String fullName, String email, String username, String password, String role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.username = username;
        this.password = password;
    }
    
    public User(String fullName, String email, String username, String password, String role) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.username = username;
        this.password = password;
    }
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
