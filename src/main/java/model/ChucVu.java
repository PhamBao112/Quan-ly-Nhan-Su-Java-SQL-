/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class ChucVu {
    private int maCV;
    private String tenCV;
    private double phuCap;
    private String ghiChu;

    // 1. Hàm rỗng
    public ChucVu() {
    }

    // 2. Hàm 3 tham số (Dành cho form Nhân Viên gọi)
    public ChucVu(int maCV, String tenCV, double phuCap) {
        this.maCV = maCV;
        this.tenCV = tenCV;
        this.phuCap = phuCap;
        this.ghiChu = ""; // 3 tham số thì mặc định ghi chú rỗng
    }

    // 3. Hàm 4 tham số (Dành cho form Chức Vụ gọi)
    public ChucVu(int maCV, String tenCV, double phuCap, String ghiChu) {
        this.maCV = maCV;
        this.tenCV = tenCV;
        this.phuCap = phuCap;
        this.ghiChu = ghiChu; // Đã gán đúng biến ghiChu truyền vào
    }

    // Getter và Setter
    public int getMaCV() { return maCV; }
    public void setMaCV(int maCV) { this.maCV = maCV; }

    public String getTenCV() { return tenCV; }
    public void setTenCV(String tenCV) { this.tenCV = tenCV; }

    public double getPhuCap() { return phuCap; }
    public void setPhuCap(double phuCap) { this.phuCap = phuCap; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

    @Override
    public String toString() {
        return tenCV; 
    }
}
