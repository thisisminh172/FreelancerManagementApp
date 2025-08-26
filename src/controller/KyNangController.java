package controller;

import dao.KyNangDAO;
import java.util.List;
import model.KyNang;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author thisi
 */
public class KyNangController {
    private KyNangDAO kyNangDAO;
    // Mảng kỹ năng mẫu
    private String[] dsKyNang = {
        "Java", "Java Swing", "React", "Python", "C++", "C#", "Docker", "AWS", "K8S", "Spring Boot", "Mysql"
    };
    
    public KyNangController() {
        kyNangDAO = new KyNangDAO();
    }   
    
    
    public String[] getKyNang() {
        return dsKyNang;
    }

    public int insert(KyNang kn) {
        return kyNangDAO.insert(kn);
    }

    public int delete(int maKyNang) {
        return kyNangDAO.delete(maKyNang);
    }

    public int update(KyNang kn) {
        return kyNangDAO.update(kn);
    }

    public List<KyNang> getAll() {
        return kyNangDAO.getAll();
    }

    public KyNang findById(int id) {
        return kyNangDAO.findById(id);
    }

}
