/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.KyNang;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DBConnection;

/**
 *
 * @author thisi
 */
public class KyNangDAO {
private Connection conn;
    public KyNangDAO() {
        try {
            conn = DBConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(KyNangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<KyNang> getAll() {
        List<KyNang> list = new ArrayList<>();
        String sql = "SELECT * FROM ky_nang";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                KyNang kn = new KyNang(
                        rs.getInt("id"),
                        rs.getString("ten"),
                        rs.getString("mota")
                );
                list.add(kn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KyNangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int insert(KyNang kn) {
        String sql = "INSERT INTO ky_nang (ten, mota) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kn.getTen());
            ps.setString(2, kn.getMota());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KyNangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int delete(int id) {
        String sql = "DELETE FROM ky_nang WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KyNangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int update(KyNang kn) {
        String sql = "UPDATE ky_nang SET ten = ?, mota = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kn.getTen());
            ps.setString(2, kn.getMota());
            ps.setInt(3, kn.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KyNangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public KyNang findById(int id) {
        String sql = "SELECT * FROM ky_nang WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new KyNang(
                            rs.getInt("id"),
                            rs.getString("ten"),
                            rs.getString("mota")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(KyNangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
