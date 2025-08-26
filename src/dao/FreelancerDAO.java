package dao;

import model.Freelancer;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FreelancerDAO {

    private Connection conn;

    public FreelancerDAO() {
        try {
            conn = DBConnection.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(FreelancerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Freelancer> getAll() {
        List<Freelancer> list = new ArrayList<>();
        String sql = "SELECT * FROM freelancer";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Freelancer f = new Freelancer(
                        rs.getInt("id"),
                        rs.getString("ho_ten"),
                        rs.getString("email"),
                        rs.getString("so_dien_thoai")
                );
                list.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FreelancerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    // Thêm Freelancer
    public int insert(Freelancer f) {
        String sql = "INSERT INTO freelancer (ho_ten, email, so_dien_thoai) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getHoTen());
            ps.setString(2, f.getEmail());
            ps.setString(3, f.getSoDienThoai());
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
        String sql = "UPDATE freelancer SET ho_ten=?, email=?, so_dien_thoai=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getHoTen());
            ps.setString(2, f.getEmail());
            ps.setString(3, f.getSoDienThoai());
            ps.setInt(4, f.getId());
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
                        rs.getString("so_dien_thoai")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FreelancerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
