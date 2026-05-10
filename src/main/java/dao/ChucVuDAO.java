package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ChucVu;
import util.DBConnection;

public class ChucVuDAO {

    // 1. Lấy toàn bộ danh sách chức vụ
    public List<ChucVu> getAll() {
        List<ChucVu> list = new ArrayList<>();
        String sql = "SELECT * FROM chucvu";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                ChucVu cv = new ChucVu();
                cv.setMaCV(rs.getInt("MaCV"));
                cv.setTenCV(rs.getString("TenCV"));
                cv.setPhuCap(rs.getDouble("PhuCap"));
                cv.setGhiChu(rs.getString("GhiChu"));
                list.add(cv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Xóa chức vụ theo Mã
    public boolean deleteChucVu(int maCV) {
        String sql = "DELETE FROM chucvu WHERE MaCV = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, maCV);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. Cập nhật thông tin chức vụ
    public boolean updateChucVu(ChucVu cv) {
        String sql = "UPDATE chucvu SET TenCV = ?, PhuCap = ?, GhiChu = ? WHERE MaCV = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, cv.getTenCV());
            ps.setDouble(2, cv.getPhuCap());
            ps.setString(3, cv.getGhiChu()); 
            ps.setInt(4, cv.getMaCV());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4. Tìm kiếm chức vụ theo tên hoặc mã
    public List<ChucVu> searchChucVu(String keyword) {
        List<ChucVu> list = new ArrayList<>();
        String sql = "SELECT * FROM chucvu WHERE TenCV LIKE ? OR MaCV LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChucVu cv = new ChucVu();
                    cv.setMaCV(rs.getInt("MaCV"));
                    cv.setTenCV(rs.getString("TenCV"));
                    cv.setPhuCap(rs.getDouble("PhuCap"));
                    cv.setGhiChu(rs.getString("GhiChu"));
                    list.add(cv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}