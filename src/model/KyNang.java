/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thisi
 */
public class KyNang {
    private int id;
    private String ten;
    private String mota;

    public KyNang(int id, String ten, String mota) {
        this.id = id;
        this.ten = ten;
        this.mota = mota;
    }
    
    public KyNang(String ten, String mota) {
        this.ten = ten;
        this.mota = mota;
    }
    
    public KyNang() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
    
    
}
