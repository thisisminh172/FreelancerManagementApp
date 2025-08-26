/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.KhachHang;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConnection;

/**
 *
 * @author thisi
 */
public class KhachHangDAO {
    private Connection conn;
    public KhachHangDAO() {
        try {
            conn = DBConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<KhachHang> getAll() {
        List<KhachHang> list = new ArrayList<>();
        String sql = "SELECT * FROM khach_hang";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                KhachHang kh = new KhachHang(
                        rs.getInt("id"),
                        rs.getString("ten"),
                        rs.getString("email"),
                        rs.getString("so_dien_thoai")
                );
                list.add(kh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int insert(KhachHang kh) {
        String sql = "INSERT INTO khach_hang (ten, email, so_dien_thoai) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getTen());
            ps.setString(2, kh.getEmail());
            ps.setString(3, kh.getSoDienThoai());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int delete(int id) {
        String sql = "DELETE FROM khach_hang WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int update(KhachHang kh) {
        String sql = "UPDATE khach_hang SET ten = ?, email = ?, so_dien_thoai = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getTen());
            ps.setString(2, kh.getEmail());
            ps.setString(3, kh.getSoDienThoai());
            ps.setInt(4, kh.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public KhachHang findById(int id) {
        String sql = "SELECT * FROM khach_hang WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new KhachHang(
                            rs.getInt("id"),
                            rs.getString("ten"),
                            rs.getString("email"),
                            rs.getString("so_dien_thoai")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
