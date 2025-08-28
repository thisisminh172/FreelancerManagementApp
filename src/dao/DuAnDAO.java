/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.DuAn;
import util.DBConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Common;

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

    public List<Map<String, Object>> getAll() {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT da.*, kh.ten tenkh, kh.email emailkh, kh.so_dien_thoai sodienthoaikh FROM du_an da LEFT JOIN khach_hang kh ON da.ma_khach_hang = kh.id";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(meta.getColumnLabel(i), rs.getObject(i));
                }
                list.add(row);
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
            ps.setDate(2, java.sql.Date.valueOf(duAn.getNgayBatDau()));
            ps.setDate(3, java.sql.Date.valueOf(duAn.getNgayKetThuc()));
            ps.setInt(4, duAn.getMaKhachHang());
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
            ps.setInt(4, duAn.getMaKhachHang());
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
                            rs.getInt("ma_khach_hang"),
                        rs.getString("mota")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DuAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int clearDuAnFreelancerByDuAnId(int duanId) {
        String sql = "DELETE FROM du_an_freelancer WHERE du_an_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, duanId);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DuAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int insertDuAnFreelancer(int duanId, int freelancerId) {
        String sql = "INSERT INTO du_an_freelancer (du_an_id, freelancer_id) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, duanId);
            ps.setInt(2, freelancerId);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DuAnDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
