package nhanvien;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Locale;

public class NhanVien implements Serializable{
    private String ten;
    private String id;
    private int luong;
    private int Hinh;

    public NhanVien(String ten, String id, int luong, int hinh) {
        this.ten = ten;
        this.id = id;
        this.luong = luong;
        Hinh = hinh;
    }

    public NhanVien() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLuong() {
        return luong;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }

    public String getThongTin() {
        Locale lcVn = new Locale("vi", "VN");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(lcVn);
        return  "\nMã nhân viên:  " + this.id +
                "\nLương: " + numberFormat.format(this.getLuong());
    }


}
