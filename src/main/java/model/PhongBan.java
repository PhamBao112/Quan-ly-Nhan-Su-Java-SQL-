/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DELL
 */
public class PhongBan {
    private int maPB;
    private String tenPB;
    private String dienThoai;
    private String ghiChu;

    // Constructor khong tham so
    public PhongBan() {
    }

    // Constructor day du tham so
    public PhongBan(int maPB, String tenPB, String dienThoai, String ghiChu) {
        this.maPB = maPB;
        this.tenPB = tenPB;
        this.dienThoai = dienThoai;
        this.ghiChu = ghiChu;
    }

    // Getter va Setter
    public int getMaPB() {
        return maPB;
    }

    public void setMaPB(int maPB) {
        this.maPB = maPB;
    }

    public String getTenPB() {
        return tenPB;
    }

    public void setTenPB(String tenPB) {
        this.tenPB = tenPB;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    /**
     * Ghi de phuong thuc toString de hien thi Ten phong ban tren ComboBox
     */
    @Override
    public String toString() {
        return maPB + "---" + this.tenPB + "---" + dienThoai;
    }
    
}
