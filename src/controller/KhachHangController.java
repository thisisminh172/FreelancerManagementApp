/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.KhachHangDAO;
import java.util.List;
import model.KhachHang;

/**
 *
 * @author thisi
 */
public class KhachHangController {

    private KhachHangDAO khachHangDAO;

    public KhachHangController() {
        khachHangDAO = new KhachHangDAO();
    }

    public int insert(KhachHang kh) {
        return khachHangDAO.insert(kh);
    }

    public int delete(int maKhachHang) {
        return khachHangDAO.delete(maKhachHang);
    }

    public int update(KhachHang kh) {
        return khachHangDAO.update(kh);
    }

    public List<KhachHang> getAll() {
        return khachHangDAO.getAll();
    }

    public KhachHang findById(int id) {
        return khachHangDAO.findById(id);
    }
}
