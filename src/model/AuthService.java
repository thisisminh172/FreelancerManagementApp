/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.User;

/**
 *
 * @author thisi
 */
public class AuthService {

    public User login(String username, String password) throws Exception {
        // demo: accept one hardcoded account
        if (("admin".equalsIgnoreCase(username) || "user".equalsIgnoreCase(username)) && password.equals("123")) {
            if ("admin".equalsIgnoreCase(username)) {
                return new User(1, "Admin User", "admin@gmail.com", "admin", "123", "ADMIN");
            } else {
                return new User(2, "User User", "user@gmail.com", "user", "123", "USER");
            }
        }
        throw new Exception("Invalid email or password");
    }
}
