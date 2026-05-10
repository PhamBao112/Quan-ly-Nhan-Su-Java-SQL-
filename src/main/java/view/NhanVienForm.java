/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.NhanVienDAO;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChucVu;
import model.NhanVien;
import model.PhongBan;

/**
 *
 * @author DELL
 */
public class NhanVienForm extends javax.swing.JFrame {
    private NhanVienDAO dao = new NhanVienDAO();
    private DefaultTableModel tblModel; 
    private List<NhanVien> list;
    /**
     * Creates new form NhanVienForm
     */
    
    // 1. KHOI TAO FORM & TRANG TRI GIAO DIEN BAN DAU
    public NhanVienForm() {
        initComponents();
        
        // Đổi nền Form thành màu Trắng
        getContentPane().setBackground(java.awt.Color.WHITE); 
        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        tblNhanVien.setDefaultEditor(Object.class, null); 
        loadPhongBan();
        loadChucVu();
        initTable();
        loadTable("");

    }
    
    // 2. DO DU LIEU KHOA NGOAI TU DATABASE LEN COMBOBOX
    public void loadPhongBan() {
    try {
        java.sql.Connection conn = util.DBConnection.getConnection();
        String sql = "SELECT * FROM phongban WHERE MaPB IN (1, 2, 3)";
        java.sql.Statement st = conn.createStatement();
        java.sql.ResultSet rs = st.executeQuery(sql);

        cboPhongBan.removeAllItems();

        while (rs.next()) {
            // Lay dung ten cot tu phpMyAdmin
            int ma = rs.getInt("MaPB");
            String ten = rs.getString("TenPB");
            String dt = rs.getString("DienThoai");
            String gc = rs.getString("GhiChu");

            // Khoi tao doi tuong voi day du 4 tham so
            model.PhongBan pb = new model.PhongBan(ma, ten, dt, gc);
            
            cboPhongBan.addItem(pb);
        }
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    public void loadChucVu() {
    try {
        java.sql.Connection conn = util.DBConnection.getConnection();
        String sql = "SELECT * FROM chucvu WHERE MaCV IN (1, 2, 3)";
        java.sql.Statement st = conn.createStatement();
        java.sql.ResultSet rs = st.executeQuery(sql);

        cboChucVu.removeAllItems();

        while (rs.next()) {
            // Lay dung ten cot tu phpMyAdmin
            int ma = rs.getInt("MaCV");
            String ten = rs.getString("TenCV");
            double phuCap = rs.getDouble("PhuCap");

            model.ChucVu cv = new model.ChucVu(ma, ten, phuCap);
            cboChucVu.addItem(cv); 
        }
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    // 3. TAI DU LIEU TU DAO DO LEN GIAO DIEN BANG
    private void loadTable(String x) { 
        tblModel.setRowCount(0);
        list = dao.getAll(x); 

        for (NhanVien nv : list) {
            tblModel.addRow(new Object[]{
                nv.getMaNV(), 
                nv.getHoTen(), 
                nv.getGioiTinh(), 
                nv.getNgaySinh(), 
                nv.getTenPB(),
                nv.getTenCV(),
                String.format("%.0f", nv.getLuongCoBan()) 
            });
        }
    }
    
    // 4. DINH DANG CAU TRUC CHO BẢNG DANH SACH
    private void initTable() {
        tblModel = (DefaultTableModel) tblNhanVien.getModel();
        String[] columns = {"Mã NV", "Họ tên", "Giới tính", "Ngày sinh", "Tên PB", "Tên CV"};
        tblModel.setColumnIdentifiers(columns);

        // 1. DỌN DẸP BẢNG
        tblNhanVien.setShowVerticalLines(true); 
        tblNhanVien.setShowHorizontalLines(true);
        tblNhanVien.setGridColor(new java.awt.Color(190, 190, 190));
        
        // 2. TRANG TRÍ TIÊU ĐỀ BẢNG (HEADER)
        javax.swing.table.JTableHeader header = tblNhanVien.getTableHeader();
        header.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        header.setBackground(java.awt.Color.WHITE);
        header.setForeground(new java.awt.Color(70, 70, 70));
        header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(200, 200, 200)));

        // 3. ĐÓNG KHUNG "DANH SÁCH NHÂN VIÊN" 
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)),
            "Danh sách nhân viên", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13),
            new java.awt.Color(41, 128, 185) 
        ));
        
        // 4. CĂN CHỈNH ĐỘ RỘNG TỪNG CỘT
        javax.swing.table.TableColumnModel columnModel = tblNhanVien.getColumnModel();
        
        columnModel.getColumn(0).setPreferredWidth(50);  // Cột 0: Mã NV 
        columnModel.getColumn(1).setPreferredWidth(170); // Cột 1: Họ tên 
        columnModel.getColumn(2).setPreferredWidth(70);  // Cột 2: Giới tính
        columnModel.getColumn(3).setPreferredWidth(90);  // Cột 3: Ngày sinh
        columnModel.getColumn(4).setPreferredWidth(120); // Cột 4: Tên PB 
        columnModel.getColumn(5).setPreferredWidth(100); // Cột 5: Tên CV
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMaNV = new javax.swing.JTextField();
        lblMaNV = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblDienThoai = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblLuong = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblPhongBan = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblNgayVaoLam = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtDienThoai = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        cboGioiTinh = new javax.swing.JComboBox<>();
        cboPhongBan = new javax.swing.JComboBox<>();
        txtLuong = new javax.swing.JTextField();
        cboChucVu = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        dcsNgaySinh = new com.toedter.calendar.JDateChooser();
        dcsNgayVaoLam = new com.toedter.calendar.JDateChooser();
        btnXuat = new javax.swing.JButton();
        XuatDB = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        lblMaNV1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thông tin nhân viên");
        setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

        lblMaNV.setText("Mã NV:");

        lblHoTen.setText("Họ và tên:");

        jLabel3.setText("Địa chỉ:");

        lblDienThoai.setText("Số điện thoại:");

        lblEmail.setText("Email:");

        lblLuong.setText("Lương:");

        lblGioiTinh.setText("Giới tính:");

        lblPhongBan.setText("Phòng ban:");

        lblChucVu.setText("Chức vụ:");

        lblNgaySinh.setText("Ngày sinh:");

        lblNgayVaoLam.setText("Ngày vào làm:");

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Thông tin nhân viên");

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ tên ", "Giới tính", "Ngày sinh"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        btnXuat.setText("Xuất Excel");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        XuatDB.setText("Xuất Excel từ DB");
        XuatDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XuatDBActionPerformed(evt);
            }
        });

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setText("Tìm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        lblMaNV1.setText("Tìm kiếm:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblNgaySinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblMaNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHoTen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDienThoai, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                        .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lblChucVu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPhongBan, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(lblGioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(lblNgayVaoLam))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnXuat)
                        .addGap(9, 9, 9)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThem)
                        .addGap(28, 28, 28)
                        .addComponent(btnSua)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnXoa)
                                .addGap(28, 28, 28)
                                .addComponent(btnLamMoi)
                                .addGap(33, 33, 33)
                                .addComponent(XuatDB))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(313, 313, 313))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addComponent(txtDiaChi)
                            .addComponent(txtDienThoai)
                            .addComponent(txtEmail)
                            .addComponent(txtLuong)
                            .addComponent(cboPhongBan, 0, 210, Short.MAX_VALUE)
                            .addComponent(cboChucVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaNV)
                            .addComponent(cboGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dcsNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dcsNgayVaoLam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMaNV1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(btnSearch))
                            .addComponent(jScrollPane1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMaNV)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch)
                    .addComponent(lblMaNV1))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblHoTen))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDienThoai)
                            .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblEmail)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLuong)
                            .addComponent(txtLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGioiTinh)
                            .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPhongBan)
                            .addComponent(cboPhongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblChucVu)
                            .addComponent(cboChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNgaySinh)
                            .addComponent(dcsNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNgayVaoLam)
                            .addComponent(dcsNgayVaoLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(btnXoa)
                    .addComponent(btnLamMoi)
                    .addComponent(btnXuat)
                    .addComponent(XuatDB))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        try {
        // --- 1. KIỂM TRA ĐIỀU KIỆN BẮT BUỘC TRƯỚC KHI THÊM ---
        if (dcsNgaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Ngày sinh trước khi thêm!");
            dcsNgaySinh.requestFocus(); 
            return; 
        }
        
        if (dcsNgayVaoLam.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn Ngày vào làm trước khi thêm!");
            dcsNgayVaoLam.requestFocus(); 
            return; 
        }

        // --- 2. NẾU ĐÃ NHẬP ĐỦ RỒI LẤY DỮ LIỆU ---
        NhanVien nv = new NhanVien();
        nv.setHoTen(txtHoTen.getText());
        nv.setGioiTinh(cboGioiTinh.getSelectedItem().toString());
        nv.setNgaySinh(dcsNgaySinh.getDate());
        nv.setDiaChi(txtDiaChi.getText());
        nv.setDienThoai(txtDienThoai.getText());
        nv.setEmail(txtEmail.getText());
        nv.setNgayVaoLam(dcsNgayVaoLam.getDate());
        
        // Xử lý Lương an toàn 
        String luongStr = txtLuong.getText();
        nv.setLuongCoBan(luongStr.isEmpty() ? 0 : Double.parseDouble(luongStr));

        // Ép kiểu trực tiếp về đối tượng
        PhongBan pb = (PhongBan) cboPhongBan.getSelectedItem();
        if (pb != null) {
            nv.setMaPB(pb.getMaPB()); 
        }

        ChucVu cv = (ChucVu) cboChucVu.getSelectedItem();
        if (cv != null) {
            nv.setMaCV(cv.getMaCV()); 
        }

        // --- 3. THỰC HIỆN THÊM VÀO DATABASE ---
        if (dao.insert(nv)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công!");
            loadTable(""); // Tải lại bảng
            btnLamMoiActionPerformed(evt); 
        } else {
            JOptionPane.showMessageDialog(this, "Thêm THẤT BẠI! Vui lòng kiểm tra lại thông tin.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Lỗi định dạng: " + e.getMessage());
    }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed

        try {
        NhanVien nv = new NhanVien();
        // 1. Lay MaNV hien tai 
        nv.setMaNV(Integer.parseInt(txtMaNV.getText()));
        
        // 2. Phai set DAY DU cac thong tin tu Form vao doi tuong
        nv.setHoTen(txtHoTen.getText());
        nv.setGioiTinh(cboGioiTinh.getSelectedItem().toString());
        nv.setNgaySinh(dcsNgaySinh.getDate());
        nv.setDiaChi(txtDiaChi.getText()); 
        nv.setDienThoai(txtDienThoai.getText()); 
        nv.setEmail(txtEmail.getText()); 
        nv.setNgayVaoLam(dcsNgayVaoLam.getDate()); 
        nv.setLuongCoBan(Double.parseDouble(txtLuong.getText()));

        // 3. Lay doi tuong tu ComboBox 
        PhongBan pb = (PhongBan) cboPhongBan.getSelectedItem();
        nv.setMaPB(pb.getMaPB());
        ChucVu cv = (ChucVu) cboChucVu.getSelectedItem();
        nv.setMaCV(cv.getMaCV());

        // 4. Goi DAO thuc hien cap nhat
        if (dao.update(nv)) {
            JOptionPane.showMessageDialog(this, "Cap nhat thanh cong!");
            loadTable("" );
        } else {
            JOptionPane.showMessageDialog(this, "Cap nhat THAT BAI! Kiem tra lai SQL.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Loi: " + e.getMessage());
    }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
        // 1. Lấy mã nhân viên từ ô nhập liệu
        int maNV = Integer.parseInt(txtMaNV.getText());

        // 2. Hiện hộp thoại xác nhận xóa 
        int confirm = JOptionPane.showConfirmDialog(
            this, 
            "Bạn có chắc chắn muốn xóa nhân viên có mã " + maNV + " này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION
        );

        // 3. Nếu người dùng chọn "Yes"
        if (confirm == JOptionPane.YES_OPTION) {
            // Gọi hàm xóa từ DAO
            if (dao.delete(maNV)) {
                // 4. Thông báo chi tiết mã đã xóa theo ý bạn
                JOptionPane.showMessageDialog(this, "Đã xóa thành công Mã NV " + maNV);
                
                // 5. Cập nhật lại bảng dữ liệu
                     loadTable("");
                
                // (Tùy chọn) Xóa trắng các ô nhập sau khi xóa thành công
                btnLamMoiActionPerformed(evt);
            }
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa từ danh sách!");
    }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        int row = tblNhanVien.getSelectedRow();
            if (row < 0) return;

            // 1. Lay MaNV tu cot dau tien cua dong dang chon
            int maNV = Integer.parseInt(tblNhanVien.getValueAt(row, 0).toString());

            // 2. Truy van Database lay thong tin FULL
            NhanVien nv = dao.getById(maNV);

            if (nv != null) {
                // 3. Do du lieu vao tat ca cac o (Ke ca cac o khong hien tren bang)
                txtMaNV.setText(String.valueOf(nv.getMaNV()));
                txtHoTen.setText(nv.getHoTen());
                txtDiaChi.setText(nv.getDiaChi()); 
                txtDienThoai.setText(nv.getDienThoai()); 
                txtEmail.setText(nv.getEmail()); 
                txtLuong.setText(String.format("%.0f", nv.getLuongCoBan()));

                cboGioiTinh.setSelectedItem(nv.getGioiTinh());
                dcsNgaySinh.setDate(nv.getNgaySinh());
                dcsNgayVaoLam.setDate(nv.getNgayVaoLam()); 

                // 4. Dong bo ComboBox Phong ban (So sanh ID)
                for (int i = 0; i < cboPhongBan.getItemCount(); i++) {
                    PhongBan pb = cboPhongBan.getItemAt(i);
                    if (pb.getMaPB() == nv.getMaPB()) {
                        cboPhongBan.setSelectedIndex(i);
                        break;
                    }
                }

                // 5. Dong bo ComboBox Chuc vu (So sanh ID)
                for (int i = 0; i < cboChucVu.getItemCount(); i++) {
                    ChucVu cv = cboChucVu.getItemAt(i);
                    if (cv.getMaCV() == nv.getMaCV()) {
                        cboChucVu.setSelectedIndex(i);
                        break;
                    }
                }
    }
    }//GEN-LAST:event_tblNhanVienMouseClicked
    
    private void setSelectedPhongBan(int maPB) {
    for (int i = 0; i < cboPhongBan.getItemCount(); i++) {
        // Lấy trực tiếp đối tượng PhongBan từ ComboBox để so sánh ID
        PhongBan pb = (PhongBan) cboPhongBan.getItemAt(i);
        if (pb.getMaPB() == maPB) {
            cboPhongBan.setSelectedItem(pb);
            break;
        }
    }
}

private void setSelectedChucVu(int maCV) {
    for (int i = 0; i < cboChucVu.getItemCount(); i++) {
        // Lấy trực tiếp đối tượng ChucVu từ ComboBox để so sánh ID
        ChucVu cv = (ChucVu) cboChucVu.getItemAt(i);
        if (cv.getMaCV() == maCV) {
            cboChucVu.setSelectedItem(cv);
            break;
        }
    }
}
    
    
    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        
        txtMaNV.setText("");
        txtHoTen.setText("");
        txtDiaChi.setText("");
        txtLuong.setText("");
        txtDienThoai.setText("");
        txtEmail.setText("");

        // 2. Dua cac o ngay thang ve trang thai trong
        dcsNgaySinh.setDate(null);
        dcsNgayVaoLam.setDate(null);

        // 3. Reset cac o lua chon ve vi tri dau tien (index 0)
        cboChucVu.setSelectedIndex(0);
        cboPhongBan.setSelectedIndex(0);
        cboGioiTinh.setSelectedIndex(0);

        // 4. (Tuy chon) Cho phep nhap lai Ma NV neu ban dang de disable
        txtMaNV.setEditable(true);

    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        util.XuatExcel.exportJTableToExcel(tblNhanVien);
    }//GEN-LAST:event_btnXuatActionPerformed

    private void XuatDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XuatDBActionPerformed
        // 1. Tạo danh sách các lựa chọn để người dùng chọn
        String[] options = {"Nhân viên (Đầy đủ)", "Phòng ban", "Chức vụ", "Hủy"};

            // 2. Hiện hộp thoại cho người dùng chọn bảng muốn xuất Excel
            int choice = javax.swing.JOptionPane.showOptionDialog(
                this, 
                "Bạn muốn xuất dữ liệu từ bảng nào trong Database?", 
                "Xuất dữ liệu từ Database", 
                javax.swing.JOptionPane.DEFAULT_OPTION, 
                javax.swing.JOptionPane.QUESTION_MESSAGE, 
                null, 
                options, 
                options[0]
        );

        // 3. Gọi hàm tương ứng trong class XuatExcel dựa trên lựa chọn
        switch (choice) {
            case 0: // Nhân viên
                util.XuatExcel.exportNhanVien();
                break;
            case 1: // Phòng ban
                util.XuatExcel.exportPhongBan();
                break;
            case 2: // Chức vụ
                util.XuatExcel.exportChucVu();
                break;
            default: // Hủy hoặc đóng hộp thoại
                break;
    }
    }//GEN-LAST:event_XuatDBActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

            // 1. Lấy từ khóa người dùng nhập và xóa khoảng trắng ở 2 đầu
            String keyword = txtSearch.getText().trim();

            // 2. Nếu không nhập gì, tải lại toàn bộ danh sách
            if (keyword.isEmpty()) {
                loadTable(""); // Gọi hàm loadTable 
                System.out.println("Hien thi lai toan bo danh sach nhan vien.");
                return;
            }

            // 3. Xóa dữ liệu cũ trên bảng
            tblModel.setRowCount(0);

            // 4. Lấy danh sách tìm kiếm từ DAO 
            list = dao.getAll(keyword); 
            System.out.println("Tim kiem nhan vien voi tu khoa: " + keyword + " - Ket qua: " + list.size());

            // 5. Đổ dữ liệu mới vào bảng 
            for (NhanVien nv : list) {
                tblModel.addRow(new Object[]{
                    nv.getMaNV(), 
                    nv.getHoTen(), 
                    nv.getGioiTinh(), 
                    nv.getNgaySinh(), 
                    nv.getTenPB(), // Đã lấy trực tiếp từ Database
                    nv.getTenCV()  // Đã lấy trực tiếp từ Database
                });
            }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        btnSearch.doClick();
    }//GEN-LAST:event_txtSearchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
                com.formdev.flatlaf.FlatIntelliJLaf.setup(); 
            } catch (Exception ex) {
                System.err.println("Lỗi khởi tạo giao diện FlatLaf");
                ex.printStackTrace();
            }

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new FMainQLNS().setVisible(true);
                }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton XuatDB;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuat;
    private javax.swing.JComboBox<ChucVu> cboChucVu;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private javax.swing.JComboBox<PhongBan> cboPhongBan;
    private com.toedter.calendar.JDateChooser dcsNgaySinh;
    private com.toedter.calendar.JDateChooser dcsNgayVaoLam;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblDienThoai;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblLuong;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblMaNV1;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblNgayVaoLam;
    private javax.swing.JLabel lblPhongBan;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtLuong;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
