package dao;

import model.Freelancer;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.KyNang;
import util.Common;

public class FreelancerDAO {

    private Connection conn;

    public FreelancerDAO() {
        try {
            conn = DBConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(FreelancerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Map<String, Object>> getAll() {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "WITH f_kn AS (\n"
                + "	SELECT fk.freelancer_id as freelancerid,\n"
                + "    GROUP_CONCAT(k.ten SEPARATOR ', ') as dskynang\n"
                + "    FROM freelancer_kynang fk LEFT JOIN ky_nang k\n"
                + "    ON fk.kynang_id = k.id\n"
                + "    GROUP BY fk.freelancer_id\n"
                + "),\n"
                + "f_da AS (\n"
                + "	SELECT f.id freelancerid,\n"
                + "    da.ten tenduan,\n"
                + "    CASE WHEN da_f.du_an_id IS NULL THEN 'Sẵn sàng' ELSE 'Bận' END AS trangthai\n"
                + "    FROM freelancer f \n"
                + "    LEFT JOIN du_an_freelancer da_f ON f.id = da_f.freelancer_id\n"
                + "    LEFT JOIN du_an da ON da_f.du_an_id = da.id\n"
                + "),\n"
                + "f_info AS (\n"
                + "SELECT f.id, f.ho_ten, f.email, f.so_dien_thoai, f_kn.dskynang, f_da.tenduan, f_da.trangthai\n"
                + "FROM freelancer f\n"
                + "LEFT JOIN f_kn ON f.id = f_kn.freelancerid\n"
                + "LEFT JOIN f_da ON f.id = f_da.freelancerid\n"
                + ")\n"
                + "SELECT id, ho_ten, email, so_dien_thoai, dskynang, trangthai FROM f_info\n"
                + "GROUP BY id, ho_ten, email, so_dien_thoai, dskynang, trangthai";
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
            Logger.getLogger(FreelancerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Thêm Freelancer
    public int insert(Freelancer f) {
        String sql = "INSERT INTO freelancer (ho_ten, email, so_dien_thoai, gioi_tinh, dia_chi, ngay_sinh) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getHoTen());
            ps.setString(2, f.getEmail());
            ps.setString(3, f.getSoDienThoai());
            ps.setString(4, f.getGioiTinh());
            ps.setString(5, f.getDiaChi());
            ps.setDate(6, Date.valueOf(f.getNgaySinh()));
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FreelancerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // Xóa Freelancer
    public int delete(int maFreelancer) {
        String sql = "DELETE FROM freelancer WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maFreelancer);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FreelancerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // Cập nhật Freelancer
    public int update(Freelancer f) {
        String sql = "UPDATE freelancer SET ho_ten=?, email=?, so_dien_thoai=?, gioi_tinh=?, dia_chi=?, ngay_sinh=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getHoTen());
            ps.setString(2, f.getEmail());
            ps.setString(3, f.getSoDienThoai());
            ps.setString(4, f.getGioiTinh());
            ps.setString(5, f.getDiaChi());
            ps.setDate(6, Date.valueOf(f.getNgaySinh()));
            ps.setInt(7, f.getId());
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FreelancerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    // Tim Freelancer qua ID
    public Freelancer findById(String ma) {
        String sql = "SELECT * FROM freelancer WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Freelancer(
                        rs.getInt("id"),
                        rs.getString("ho_ten"),
                        rs.getString("email"),
                        rs.getString("so_dien_thoai"),
                        Common.convertStringToLocalDate(rs.getString("ngay_sinh")),
                        rs.getString("gioi_tinh"),
                        rs.getString("dia_chi")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FreelancerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<KyNang> getFreelancerKyNangById(int id) {
        List<KyNang> list = new ArrayList<>();
        String sql = "SELECT k.id, k.ten FROM ky_nang k "
                + "JOIN freelancer_kynang fk ON k.id = fk.kynang_id "
                + "WHERE fk.freelancer_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KyNang kn = new KyNang();
                kn.setId(rs.getInt("id"));
                kn.setTen(rs.getString("ten"));
                list.add(kn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FreelancerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int deleteAllFreelancerKyNangById(int id) {
        String sql = "DELETE FROM freelancer_kynang WHERE freelancer_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FreelancerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int insertFreelancerKyNang(int freelancerId, int kyNangId) {
        String sql = "INSERT INTO freelancer_kynang (freelancer_id, kynang_id) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, freelancerId);
            ps.setInt(2, kyNangId);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FreelancerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
