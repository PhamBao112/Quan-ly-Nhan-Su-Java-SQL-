package util;

import java.io.FileOutputStream;
import java.sql.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XuatExcel {

    // --- 1. XUẤT TỪ JTABLE (Dùng cho bảng đang hiển thị trên màn hình) ---
    public static void exportJTableToExcel(JTable table) {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("KetQuaHienTai");
            TableModel model = table.getModel();

            // Tạo Tiêu đề
            Row headerRow = sheet.createRow(0);
            CellStyle style = createHeaderStyle(workbook);
            for (int i = 0; i < model.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(model.getColumnName(i));
                cell.setCellStyle(style);
            }

            // Đổ dữ liệu
            for (int i = 0; i < model.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Object val = model.getValueAt(i, j);
                    if (val != null) row.createCell(j).setCellValue(val.toString());
                }
            }

            finishExport(workbook, sheet, model.getColumnCount(), "Export_Table.xlsx");
        } catch (Exception e) { showError(e); }
    }

    // --- 2. XUẤT NHÂN VIÊN (Truy vấn JOIN 3 bảng từ Database) ---
    public static void exportNhanVien() {
        String sql = "SELECT nv.MaNV, nv.HoTen, nv.NgaySinh, nv.GioiTinh, nv.DiaChi, "
                   + "nv.DienThoai, nv.Email, pb.TenPB, cv.TenCV, nv.LuongCoBan "
                   + "FROM nhanvien nv "
                   + "JOIN phongban pb ON nv.MaPB = pb.MaPB "
                   + "JOIN chucvu cv ON nv.MaCV = cv.MaCV"; // Lấy đủ tên thay vì ID

        String[] headers = {"Mã NV", "Họ tên", "Ngày sinh", "Giới tính", "Địa chỉ", "SĐT", "Email", "Phòng ban", "Chức vụ", "Lương"};
        exportFromDatabase(sql, "BaoCao_NhanVien", headers);
    }

    // --- 3. XUẤT PHÒNG BAN (Truy vấn từ Database) ---
    public static void exportPhongBan() {
        String sql = "SELECT * FROM phongban";
        String[] headers = {"Mã PB", "Tên Phòng", "Số ĐT", "Ghi chú"};
        exportFromDatabase(sql, "DanhSach_PhongBan", headers);
    }

    // --- 4. XUẤT CHỨC VỤ (Truy vấn từ Database) ---
    public static void exportChucVu() {
        String sql = "SELECT * FROM chucvu";
        String[] headers = {"Mã CV", "Tên Chức Vụ", "Phụ Cấp"};
        exportFromDatabase(sql, "DanhSach_ChucVu", headers);
    }

    // --- CÁC HÀM HỖ TRỢ ĐỂ CODE KHÔNG BỊ LẶP ---

    private static void exportFromDatabase(String sql, String sheetName, String[] headers) {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(sheetName);
            
            // Header
            Row headerRow = sheet.createRow(0);
            CellStyle style = createHeaderStyle(workbook);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(style);
            }

            // Data
            int rowIdx = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowIdx++);
                for (int i = 0; i < headers.length; i++) {
                    row.createCell(i).setCellValue(rs.getString(i + 1));
                }
            }

            finishExport(workbook, sheet, headers.length, sheetName + ".xlsx");
        } catch (Exception e) { showError(e); }
    }

    private static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true); // In đậm tiêu đề Excel
        style.setFont(font);
        return style;
    }

    private static void finishExport(Workbook workbook, Sheet sheet, int colCount, String defaultName) throws Exception {
        for (int i = 0; i < colCount; i++) sheet.autoSizeColumn(i); // Tự động giãn cột

        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new java.io.File(defaultName));
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try (FileOutputStream out = new FileOutputStream(chooser.getSelectedFile())) {
                workbook.write(out);
                workbook.close();
                JOptionPane.showMessageDialog(null, "Xuất Excel thành công!");
            }
        }
    }

    private static void showError(Exception e) {
        JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
        e.printStackTrace();
    }
}