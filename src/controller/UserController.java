/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDAO;
import java.util.List;
import model.User;

/**
 *
 * @author thisi
 */
public class UserController {

    private UserDAO userDAO;

    public UserController() {
        userDAO = new UserDAO();
    }

    public int insert(User user) {
        return userDAO.insert(user);
    }

    public int delete(int userId) {
        return userDAO.delete(userId);
    }

    public int update(User user) {
        return userDAO.update(user);
    }

    public List<User> getAll() {
        return userDAO.getAll();
    }

    public User findById(int id) {
        return userDAO.findById(id);
    }
}
