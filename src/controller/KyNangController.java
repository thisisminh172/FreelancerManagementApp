package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author thisi
 */
public class KyNangController {
    private String[] dsKyNang = {
        "Java", "Java Swing", "React", "Python", "C++", "C#", "Docker", "AWS", "K8S", "Spring Boot", "Mysql"
    };
    
    public KyNangController() {
        // return dao
    }
    
    public String[] getKyNang() {
        return dsKyNang;
    }
}
