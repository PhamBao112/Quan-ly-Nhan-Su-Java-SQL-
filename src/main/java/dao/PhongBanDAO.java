package dao;

import java.util.List;
import java.util.ArrayList;

public class PhongBanDAO {

    // 1. Hàm lấy danh sách để đổ lên bảng (Code của bạn, giữ nguyên vì đã chuẩn)
    public List<model.PhongBan> getAll() {
        List<model.PhongBan> list = new ArrayList<>();
        String sql = "SELECT * FROM phongban"; 
        
        try (java.sql.Connection con = util.DBConnection.getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement(sql);
             java.sql.ResultSet rs = ps.executeQuery()) {
             
            while (rs.next()) {
                model.PhongBan pb = new model.PhongBan(
                    rs.getInt("MaPB"),
                    rs.getString("TenPB"),
                    rs.getString("DienThoai"),
                    rs.getString("GhiChu")
                );
                list.add(pb);
            }
        } catch (Exception e) {
            System.out.println("Lỗi lấy danh sách phòng ban: " + e.getMessage());
        }
        return list;
    }

    // 2. Hàm gọi Procedure để Thêm phòng ban mới (Thiếu trong code của bạn)
    public boolean insertPhongBan(model.PhongBan pb) {
        String sql = "{call sp_ThemPhongBan(?, ?, ?)}";
        
        try (java.sql.Connection con = util.DBConnection.getConnection();
             java.sql.CallableStatement cstmt = con.prepareCall(sql)) {
            
            cstmt.setString(1, pb.getTenPB());
            cstmt.setString(2, pb.getDienThoai());
            cstmt.setString(3, pb.getGhiChu());
            
            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (Exception e) {
            System.out.println("Lỗi gọi Procedure Thêm Phòng Ban: " + e.getMessage());
            return false;
        }
    }
    
    // 3. Hàm gọi Procedure để Xóa phòng ban
    public boolean deletePhongBan(int maPB) {
        String sql = "{call sp_XoaPhongBan(?)}"; // Gọi procedure Xóa
        
        try (java.sql.Connection con = util.DBConnection.getConnection();
             java.sql.CallableStatement cstmt = con.prepareCall(sql)) {
            
            cstmt.setInt(1, maPB);
            
            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (Exception e) {
            System.out.println("Lỗi gọi Procedure Xóa Phòng Ban: " + e.getMessage());
            return false;
        }
    }
    
    // 4. Hàm gọi Procedure để Sửa (Cập nhật) phòng ban
    public boolean updatePhongBan(model.PhongBan pb) {
        // Gọi procedure có 4 tham số (?)
        String sql = "{call sp_SuaPhongBan(?, ?, ?, ?)}"; 
        
        try (java.sql.Connection con = util.DBConnection.getConnection();
             java.sql.CallableStatement cstmt = con.prepareCall(sql)) {
            
            // Truyền dữ liệu vào 4 dấu ? theo đúng thứ tự đã khai báo trong MySQL
            cstmt.setInt(1, pb.getMaPB());
            cstmt.setString(2, pb.getTenPB());
            cstmt.setString(3, pb.getDienThoai());
            cstmt.setString(4, pb.getGhiChu());
            
            // Thực thi lệnh Update
            int rowsAffected = cstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (Exception e) {
            System.out.println("Lỗi gọi Procedure Sửa Phòng Ban: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // 5. Hàm gọi Procedure để Tìm kiếm
    public List<model.PhongBan> searchPhongBan(String keyword) {
        List<model.PhongBan> list = new java.util.ArrayList<>();
        String sql = "{call sp_TimKiemPhongBan(?)}"; 
        
        try (java.sql.Connection con = util.DBConnection.getConnection();
             java.sql.CallableStatement cstmt = con.prepareCall(sql)) {
            
            cstmt.setString(1, keyword);
            
            // Vì lệnh SELECT trả về bảng dữ liệu nên dùng executeQuery()
            try (java.sql.ResultSet rs = cstmt.executeQuery()) {
                while (rs.next()) {
                    model.PhongBan pb = new model.PhongBan(
                        rs.getInt("MaPB"),
                        rs.getString("TenPB"),
                        rs.getString("DienThoai"),
                        rs.getString("GhiChu")
                    );
                    list.add(pb);
                }
            }
        } catch (Exception e) {
            System.out.println("Lỗi gọi Procedure Tìm kiếm Phòng Ban: " + e.getMessage());
        }
        return list;
    }
}