package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iter.tuongnguyen.cta.R;

import java.util.ArrayList;

import nhanvien.NhanVien;

public class NhanVienAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    ArrayList<NhanVien> arrNhanVien;

    public NhanVienAdapter(Context context, int layout, ArrayList<NhanVien> arrNhanVien) {
        this.context = context;
        this.layout = layout;
        this.arrNhanVien = arrNhanVien;
    }

    @Override
    public int getCount() {
        return arrNhanVien.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        ImageView imgAnh = convertView.findViewById(R.id.imgAnh);
        TextView txtTen = convertView.findViewById(R.id.txtTen);
        TextView  txtThongTin = convertView.findViewById(R.id.txtThongTin);
        NhanVien nhanVien = arrNhanVien.get(position);
        imgAnh.setImageResource(nhanVien.getHinh());
        txtTen.setText(nhanVien.getTen());
        txtThongTin.setText(nhanVien.getThongTin());

        return convertView;
    }
}
