package com.mycompany.quanly_nhansu;

import dao.NhanVienDAO;
import java.sql.Connection;
import java.util.Date; 
import java.util.List;
import model.NhanVien;
import util.DBConnection;
import view.FMainQLNS;
import view.NhanVienForm;

public class QuanLy_NhanSu {

    public static void main(String[] args) {
        // --- PHẦN 1: KIỂM TRA KẾT NỐI ---
//        Connection con = DBConnection.getConnection();
//        if (con == null) return; 
//
//        NhanVienDAO dao = new NhanVienDAO();
//
//        // --- BƯỚC 1: XEM DANH SÁCH BAN ĐẦU ---
//        System.out.println("\n[READ] Danh sách nhân viên ban đầu:");
//        printList(dao.getAll());
//
//        // --- BƯỚC 2: THÊM 4 NGƯỜI (DỮ LIỆU NULL) ---
////        System.out.println("\n[CREATE] Đang thêm 4 nhân viên mới (để trống thông tin)...");
////        String[] tenMoi = {"Nguyễn Văn Sáu", "Trần Thị Bảy", "Lê Văn Tám", "Hoàng Thị Chín"};
////
////        for (String ten : tenMoi) {
////            NhanVien nv = new NhanVien();
////            nv.setHoTen(ten);
////            nv.setGioiTinh("Chưa rõ");
////            nv.setNgaySinh(new Date());
////            
////            // Thiết lập NULL để test Cập Nhật sau
////            nv.setDiaChi(null); 
////            nv.setDienThoai(null); 
////            nv.setEmail(null); 
////            
////            nv.setMaPB(1); 
////            nv.setMaCV(3); 
////            nv.setNgayVaoLam(new Date());
////            nv.setLuongCoBan(0.0);
////
////            dao.insert(nv);
////        }
//
//        // --- BƯỚC 3: TEST CẬP NHẬT (Chỉ sửa 1 người cụ thể) ---
//
////        int[] idsToUpdate = {1, 2, 3, 4, 5, 27, 28, 29, 30};
////        
////        System.out.println("\n[UPDATE] Đang tiến hành cập nhật thông tin chi tiết...");
////        
////        for (int id : idsToUpdate) {
////            NhanVien nv = new NhanVien();
////            nv.setMaNV(id);
////            
////            // Thiết lập thông tin giả định để lấp đầy các cột NULL
////            nv.setHoTen("Nhân viên ID " + id);
////            nv.setGioiTinh(id % 2 == 0 ? "Nữ" : "Nam");
////            nv.setNgaySinh(new Date());
////            nv.setDiaChi("Địa chỉ mẫu " + id);
////            nv.setDienThoai("090" + id + "12345"); // Cập nhật SĐT thay vì NULL
////            nv.setEmail("nv" + id + "@example.com"); // Cập nhật Email thay vì NULL
////            nv.setMaPB(1); 
////            nv.setMaCV(1);
////            nv.setNgayVaoLam(new Date()); // Cập nhật Ngày vào làm
////            nv.setLuongCoBan(10000000 + (id * 100000));
////
////            if (dao.update(nv)) {
////                System.out.println(" ✅ Đã cập nhật xong ID: " + id);
////            } else {
////                System.out.println(" ❌ Thất bại tại ID: " + id);
////            }
////        }
////
////        System.out.println("\n[READ] Danh sách nhân viên sau khi đã CẬP NHẬT ĐẦY ĐỦ:");
////        printList(dao.getAll());
////        
////        System.out.println("\n=== KẾT THÚC TEST CẬP NHẬT ===");
////    }
//        
//        // --- BƯỚC 4: XEM DANH SÁCH TRƯỚC KHI XÓA ---   TEST Xóa 
//        System.out.println("\n[READ] Danh sách nhân viên trước khi dọn dẹp:");
//        printList(dao.getAll());
//
//        // Chúng ta sẽ xóa các ID rác từ 27 đến 30
//        int[] idsToDelete = {27, 28, 29, 30}; 
//        
//        System.out.println("\n[DELETE] Đang tiến hành xóa các nhân viên test...");
//        
//        for (int id : idsToDelete) {
//            if (dao.delete(id)) { // Gọi hàm delete từ NhanVienDAO
//                System.out.println(" ✅ Đã xóa thành công nhân viên mã: " + id);
//            } else {
//                System.out.println(" ❌ Không tìm thấy hoặc lỗi khi xóa ID: " + id);
//            }
//        }
//   // --- HIỂN THỊ KẾT QUẢ CUỐI CÙNG ---
//        System.out.println("\n[READ] Danh sách nhân viên sau khi đã dọn dẹp:");
//        List<NhanVien> dsSauXoa = dao.getAll();
//        printList(dsSauXoa);
//        
//        System.out.println("\n=== KẾT THÚC TEST XÓA ===");
//        System.out.println("Tổng cộng còn lại: " + dsSauXoa.size() + " nhân viên.");
//    }
//        
//    
//
//    private static void printList(List<NhanVien> list) {
//        if (list.isEmpty()) {
//            System.out.println("Danh sách trống.");
//        } else {
//            for (NhanVien nv : list) {
//                System.out.println(" ID: " + nv.getMaNV() 
//                                 + " | Tên: " + nv.getHoTen() 
//                                 + " | SĐT: " + nv.getDienThoai() 
//                                 + " | Lương: " + nv.getLuongCoBan());
//            }
//        }
    
    
    FMainQLNS nv = new FMainQLNS();
    nv.setVisible(true);

    }

}