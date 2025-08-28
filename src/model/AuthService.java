/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.UserController;
import model.User;

/**
 *
 * @author thisi
 */
public class AuthService {
    
    UserController userController;
    
    public AuthService() {
        userController = new UserController();
    }

    public User login(String username, String password) throws Exception {
        // demo: accept one hardcoded account
        User user = userController.findByUsername(username);
        
        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            return user;
        }
        throw new Exception("Invalid email or password");
    }
}
