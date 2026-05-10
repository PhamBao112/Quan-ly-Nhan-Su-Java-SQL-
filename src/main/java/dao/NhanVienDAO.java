package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.NhanVien;
import util.DBConnection;

public class NhanVienDAO {

    // 1. Thêm mới nhân viên (Insert)
    public boolean insert(NhanVien nv) {
        String sql = "INSERT INTO nhanvien (HoTen, GioiTinh, NgaySinh, DiaChi, DienThoai, Email, MaPB, MaCV, NgayVaoLam, LuongCoBan) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getGioiTinh());
            ps.setDate(3, new java.sql.Date(nv.getNgaySinh().getTime()));
            ps.setString(4, nv.getDiaChi());
            ps.setString(5, nv.getDienThoai());
            ps.setString(6, nv.getEmail());
            ps.setInt(7, nv.getMaPB());
            ps.setInt(8, nv.getMaCV());
            ps.setDate(9, new java.sql.Date(nv.getNgayVaoLam().getTime()));
            ps.setDouble(10, nv.getLuongCoBan());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // 2. Cập nhật thông tin (Update)
    public boolean update(NhanVien nv) {
        String sql = "UPDATE nhanvien SET HoTen=?, GioiTinh=?, NgaySinh=?, DiaChi=?, "
           + "DienThoai=?, Email=?, MaPB=?, MaCV=?, NgayVaoLam=?, LuongCoBan=? "
           + "WHERE MaNV=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, nv.getHoTen());
            ps.setString(2, nv.getGioiTinh());
            ps.setDate(3, new java.sql.Date(nv.getNgaySinh().getTime()));
            ps.setString(4, nv.getDiaChi());
            ps.setString(5, nv.getDienThoai());
            ps.setString(6, nv.getEmail());
            ps.setInt(7, nv.getMaPB());
            ps.setInt(8, nv.getMaCV());
            ps.setDate(9, new java.sql.Date(nv.getNgayVaoLam().getTime()));
            ps.setDouble(10, nv.getLuongCoBan());
            ps.setInt(11, nv.getMaNV());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // 3. Xóa nhân viên (Delete)
    public boolean delete(int maNV) {
        String sql = "DELETE FROM nhanvien WHERE MaNV = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maNV);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // 4. Lấy toàn bộ danh sách (Read All)
    public List<NhanVien> getAll(String x) {
    List<NhanVien> list = new ArrayList<>();
    
    // Câu lệnh gốc
    String sql = "SELECT nv.NgayVaoLam, nv.DiaChi, nv.MaPB, nv.MaCV, nv.DienThoai, nv.Email, "
               + "nv.MaNV, nv.HoTen, nv.GioiTinh, nv.NgaySinh, nv.LuongCoBan, pb.TenPB, cv.TenCV "
               + "FROM nhanvien nv "
               + "JOIN phongban pb ON nv.MaPB = pb.MaPB "
               + "JOIN chucvu cv ON nv.MaCV = cv.MaCV ";

    // Kiểm tra xem x có dữ liệu không
    boolean isSearch = (x != null && !x.trim().isEmpty());
    if (isSearch) {
        sql += "WHERE nv.MaNV = ? OR nv.HoTen LIKE ? OR nv.Email LIKE ? OR nv.DienThoai LIKE ?";
    }

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        if (isSearch) {
            String keyword = x.trim();
            
            // 1. Xử lý Mã NV an toàn
            try {
                ps.setInt(1, Integer.parseInt(keyword));
            } catch (NumberFormatException e) {
                ps.setInt(1, -1); 
            }
            
            // 2. Các trường Chữ (Tên, SĐT, Email) vẫn dùng % để tìm gần đúng
            String likeKeyword = "%" + keyword + "%";
            ps.setString(2, likeKeyword);
            ps.setString(3, likeKeyword);
            ps.setString(4, likeKeyword);
        }

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getInt("MaNV"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setDienThoai(rs.getString("DienThoai"));
                nv.setEmail(rs.getString("Email"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setNgaySinh(rs.getDate("NgaySinh"));
                nv.setNgayVaoLam(rs.getDate("NgayVaoLam"));
                nv.setLuongCoBan(rs.getDouble("LuongCoBan"));
                nv.setMaPB(rs.getInt("MaPB"));
                nv.setMaCV(rs.getInt("MaCV"));
                nv.setTenPB(rs.getString("TenPB"));
                nv.setTenCV(rs.getString("TenCV"));
                list.add(nv);
            }
        }
    } catch (Exception e) {
        System.out.println("Lỗi getAll: " + e.getMessage());
        e.printStackTrace();
    }
    return list;
}
    
    // 5.
    public NhanVien getById(int id) {
    // Truy van tat ca cac cot (*) cua nhan vien do
    String sql = "SELECT * FROM nhanvien WHERE MaNV = ?";
    try (Connection con = util.DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new NhanVien(
                rs.getInt("MaNV"), rs.getString("HoTen"), rs.getString("GioiTinh"),
                rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("DienThoai"),
                rs.getString("Email"), rs.getInt("MaPB"), rs.getInt("MaCV"),
                rs.getDate("NgayVaoLam"), rs.getDouble("LuongCoBan")
            );
        }
    } catch (Exception e) {}
    return null;
    }
}