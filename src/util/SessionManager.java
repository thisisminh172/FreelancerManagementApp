/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import model.User;

/**
 *
 * @author thisi
 */
public final class SessionManager {
    
    private static User currentUser; // null when logout
    
    private static final PropertyChangeSupport pcs = new PropertyChangeSupport(SessionManager.class);
            
    private SessionManager() {}
    
    public static User getCurrentUser() {return currentUser;}
    
    public static void setCurrentUser(User user) {
        User old = currentUser;
        currentUser = user;
        pcs.firePropertyChange("currentUser", old, user);
    }
    
    public static boolean isLoggedIn() { return currentUser != null; }
    
    public static void addListener(PropertyChangeListener l) { pcs.addPropertyChangeListener(l); }
    public static void removeListener(PropertyChangeListener l) { pcs.removePropertyChangeListener(l); }

    public static void logout() { setCurrentUser(null); }
}
