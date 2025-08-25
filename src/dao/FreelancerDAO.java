package dao;

import model.Freelancer;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FreelancerDAO {

    private Connection conn;

    public FreelancerDAO() {
        conn = DBConnection.getConnection();
    }

    public List<Freelancer> getAll() throws SQLException {
        List<Freelancer> list = new ArrayList<>();
        String sql = "SELECT * FROM Freelancer";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Freelancer f = new Freelancer(
                        rs.getInt("ma_freelancer"),
                        rs.getString("ho_ten"),
                        rs.getString("email"),
                        rs.getString("so_dien_thoai")
                );
                list.add(f);
            }
        }
        return list;
    }

    // Thêm Freelancer
    public boolean insert(Freelancer f) throws SQLException {
        String sql = "INSERT INTO Freelancer(ma_freelancer, ho_ten, email, so_dien_thoai, ky_nang, danh_gia) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, f.getId());
            ps.setString(2, f.getHoTen());
            ps.setString(3, f.getEmail());
            ps.setString(4, f.getSoDienThoai());

            return ps.executeUpdate() > 0;
        }
    }

    // Xóa Freelancer
    public boolean delete(int maFreelancer) throws SQLException {
        String sql = "DELETE FROM Freelancer WHERE ma_freelancer = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maFreelancer);
            return ps.executeUpdate() > 0;
        }
    }

    // Cập nhật Freelancer
    public boolean update(Freelancer f) throws SQLException {
        String sql = "UPDATE Freelancer SET ho_ten=?, email=?, so_dien_thoai=?, ky_nang=?, danh_gia=? WHERE ma_freelancer=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, f.getHoTen());
            ps.setString(2, f.getEmail());
            ps.setString(3, f.getSoDienThoai());

            return ps.executeUpdate() > 0;
        }
    }

    // Tim Freelancer qua ID
    public Freelancer findById(String ma) throws SQLException {
        String sql = "SELECT * FROM Freelancer WHERE ma_freelancer=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ma);;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Freelancer(
                        rs.getInt("ma_freelancer"),
                        rs.getString("ho_ten"),
                        rs.getString("email"),
                        rs.getString("so_dien_thoai")
                );
            }
            return null;
        }
    }
}
