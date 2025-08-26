/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.DuAn;
import util.DBConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thisi
 */
public class DuAnDAO {
    private Connection conn;
    public DuAnDAO() {
        try {
            conn = (Connection) DBConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DuAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<DuAn> getAll() {
        List<DuAn> list = new ArrayList<>();
        String sql = "SELECT * FROM du_an";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DuAn da = new DuAn(
                        rs.getInt("id"),
                        rs.getString("ten"),
                        rs.getDate("ngay_bat_dau").toLocalDate(),
                        rs.getDate("ngay_ket_thuc").toLocalDate(),
                        rs.getString("ma_khach_hang"),
                        rs.getString("mota")
                );
                list.add(da);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DuAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
    public int insert(DuAn duAn) {
        String sql = "INSERT INTO du_an (ten, ngay_bat_dau, ngay_ket_thuc, ma_khach_hang, mota) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, duAn.getTen());
            ps.setDate(2, Date.valueOf(duAn.getNgayBatDau()));
            ps.setDate(3, Date.valueOf(duAn.getNgayKetThuc()));
            ps.setString(4, duAn.getMaKhachHang());
            ps.setString(5, duAn.getMota());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DuAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int delete(int id) {
        String sql = "DELETE FROM du_an WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DuAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int update(DuAn duAn) {
        String sql = "UPDATE du_an SET ten = ?, ngay_bat_dau = ?, ngay_ket_thuc = ?, ma_khach_hang = ?, mota = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, duAn.getTen());
            ps.setDate(2, Date.valueOf(duAn.getNgayBatDau()));
            ps.setDate(3, Date.valueOf(duAn.getNgayKetThuc()));
            ps.setString(4, duAn.getMaKhachHang());
            ps.setString(5, duAn.getMota());
            ps.setInt(6, duAn.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DuAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        }   
        return 0;
    }
    
    public DuAn findById(int id) {
        String sql = "SELECT * FROM du_an WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new DuAn(
                        rs.getInt("id"),
                        rs.getString("ten"),
                        rs.getDate("ngay_bat_dau").toLocalDate(),
                        rs.getDate("ngay_ket_thuc").toLocalDate(),
                        rs.getString("ma_khach_hang"),
                        rs.getString("mota")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DuAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
}
