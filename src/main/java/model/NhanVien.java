/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author DELL
 */

public class NhanVien {
 
    private int maNV;
    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;
    private String diaChi;
    private String dienThoai;
    private String email;
    private int maPB;
    private int maCV;
    private Date ngayVaoLam;
    private double luongCoBan;
    private String tenPB;
    private String tenCV;
    
    // Constructor không tham số
    public NhanVien() {
    }

    // Constructor đầy đủ tham số để khởi tạo nhanh
    public NhanVien(int maNV, String hoTen, String gioiTinh, Date ngaySinh, String diaChi, 
                    String dienThoai, String email, int maPB, int maCV, 
                    Date ngayVaoLam, double luongCoBan) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.email = email;
        this.maPB = maPB;
        this.maCV = maCV;
        this.ngayVaoLam = ngayVaoLam;
        this.luongCoBan = luongCoBan;
    }

    // --- GETTER & SETTER ---

    public int getMaNV() { return maNV; }
    public void setMaNV(int maNV) { this.maNV = maNV; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getDienThoai() { return dienThoai; }
    public void setDienThoai(String dienThoai) { this.dienThoai = dienThoai; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getMaPB() { return maPB; }
    public void setMaPB(int maPB) { this.maPB = maPB; }

    public int getMaCV() { return maCV; }
    public void setMaCV(int maCV) { this.maCV = maCV; }

    public Date getNgayVaoLam() { return ngayVaoLam; }
    public void setNgayVaoLam(Date ngayVaoLam) { this.ngayVaoLam = ngayVaoLam; }

    public double getLuongCoBan() { return luongCoBan; }
    public void setLuongCoBan(double luongCoBan) { this.luongCoBan = luongCoBan; }
    
    public String getTenPB() { return tenPB; }
    public void setTenPB(String tenPB) { this.tenPB = tenPB; }

    public String getTenCV() { return tenCV; }
    public void setTenCV(String tenCV) { this.tenCV = tenCV; }
    
    
}
