package com.iter.tuongnguyen.cta;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import adapter.NhanVienAdapter;
import nhanvien.NhanVien;

public class MainActivity extends AppCompatActivity {
    private ListView lvNhanVien;
    private ArrayList<NhanVien> arrLNhanVien;
    private NhanVienAdapter nhanVienAdapter;
    private Button btnAdd, btnEdit, btnDelete;

    private int editPostion = -1;

    private static final int RQCADD = 1999;
    private static final int RQCED = 157;

    int currentPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvNhanVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPosition = position;
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPosition == -1){
                    Toast.makeText(MainActivity.this, "Vui lòng chọn đối tượng trước khi xóa!", Toast.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                ad.setTitle("Xác nhận xóa");
                ad.setMessage("Bạn có chắc chắn muốn xóa?");

                ad.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrLNhanVien.remove(currentPosition);
                        nhanVienAdapter.notifyDataSetChanged();
                        currentPosition  = -1;
                    }
                });
                ad.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                ad.show();

            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPosition == -1){
                    Toast.makeText(MainActivity.this, "Vui lòng chọn đối tượng cần chỉnh sửa!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intentED = new Intent(MainActivity.this, EditActivity.class);
                intentED.putExtra("doituongcansua", arrLNhanVien.get(currentPosition));
                startActivityForResult(intentED, RQCED);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, RQCADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null) {
            return;
        }
        if (requestCode == RQCADD && resultCode == RESULT_OK) {
            Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            NhanVien nv = (NhanVien) data.getSerializableExtra("nhanvienresult");
            arrLNhanVien.add(nv);
            nhanVienAdapter.notifyDataSetChanged();

        }else if(requestCode == RQCED && resultCode == 113){
            NhanVien nv = (NhanVien) data.getSerializableExtra("nhanviensua");
            arrLNhanVien.set(currentPosition,nv);
            nhanVienAdapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addControls() {
        lvNhanVien = findViewById(R.id.lvNhanVien);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);
        arrLNhanVien = new ArrayList<>();

        {
            arrLNhanVien.add(new NhanVien("Nguyễn Chí Phèo","112",12000000,R.drawable.avatar1));
            arrLNhanVien.add(new NhanVien("Trương Thị Nở","113",11000000,R.drawable.avatar2));
            arrLNhanVien.add(new NhanVien("Trần Lý Thông","114",10000000,R.drawable.avatar3));
            arrLNhanVien.add(new NhanVien("Lê Thạch Sanh:","115",9000000,R.drawable.avatar4));
            arrLNhanVien.add(new NhanVien("Nguyễn Thị Thẹo","116",25000000,R.drawable.avatar5));
            arrLNhanVien.add(new NhanVien("Bùi Văn Kiệm","117",8000000,R.drawable.avatar6));
            arrLNhanVien.add(new NhanVien("Ô Ba Má","118",100000000,R.drawable.avatar7));

        }

        nhanVienAdapter = new NhanVienAdapter(MainActivity.this, R.layout.nhanvien_item, arrLNhanVien);
        lvNhanVien.setAdapter(nhanVienAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(MainActivity.this);
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuItemRefresh:
                nhanVienAdapter.notifyDataSetChanged();
                break;
            case R.id.menuItemSXTen:
                Collections.sort(arrLNhanVien, new Comparator<NhanVien>() {
                    @Override
                    public int compare(NhanVien o1, NhanVien o2) {
                        return o1.getTen().compareTo(o2.getTen());
                    }
                });
                nhanVienAdapter.notifyDataSetChanged();
                break;
            case R.id.menuItemSXID:
                Collections.sort(arrLNhanVien, new Comparator<NhanVien>() {
                    @Override
                    public int compare(NhanVien o1, NhanVien o2) {
                        return o1.getId().compareTo(o2.getId());
                    }
                });
                nhanVienAdapter.notifyDataSetChanged();
                break;
            case R.id.menuItemSXTLuong:
                Collections.sort(arrLNhanVien, new Comparator<NhanVien>() {
                    @Override
                    public int compare(NhanVien o1, NhanVien o2) {
                        if(o1.getLuong() < o2.getLuong() ){
                            return -1;
                        }
                        if(o1.getLuong() > o2.getLuong()){
                            return 1;
                        }
                        return 0;
                    }
                });
                nhanVienAdapter.notifyDataSetChanged();
                break;
            case  R.id.menuItemExit:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
