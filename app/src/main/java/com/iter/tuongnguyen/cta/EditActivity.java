package com.iter.tuongnguyen.cta;

import android.content.Intent;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import nhanvien.NhanVien;

public class EditActivity extends AppCompatActivity {
    private EditText txtIputNameE, txtInputIdE, txtInputLuongE;
   private ImageView imgInputAvatarE;
   private Button btnSua;
    private static int AVTID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        addControls();
        addEvents();

    }

    private void addEvents() {
        final Intent intent = getIntent();
        NhanVien nv = (NhanVien) intent.getSerializableExtra("doituongcansua");
        if (nv == null) {
            Toast.makeText(this, "Lỗi khi nhận đối tượng!", Toast.LENGTH_SHORT).show();
            return;
        }

        txtIputNameE.setText(nv.getTen());
        txtInputIdE.setText(nv.getId());
        txtInputLuongE.setText(nv.getLuong()+"");
        imgInputAvatarE.setImageResource(nv.getHinh());

        AVTID = nv.getHinh();

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVien nv = new NhanVien();
                nv.setTen(txtIputNameE.getText().toString().trim());
                nv.setId(txtInputIdE.getText().toString().trim());
                nv.setLuong(Integer.parseInt(txtInputLuongE.getText().toString()));
                nv.setHinh(AVTID);

                intent.putExtra("nhanviensua", nv);
                setResult(113, intent);
                finish();
            }
        });

        imgInputAvatarE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAVT = new Intent(EditActivity.this, ChoseAvatarActivity.class);
                startActivityForResult(intentAVT, 157);
            }
        });

    }

    private void addControls() {
        txtInputIdE = findViewById(R.id.txtInputIDE);
        txtIputNameE = findViewById(R.id.txtInputNameE);
        txtInputLuongE = findViewById(R.id.txtSalaryE);
        imgInputAvatarE = findViewById(R.id.imgInputAvatarE);
        btnSua = findViewById(R.id.btnSuaE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 157 && resultCode == RESULT_OK && data != null) {
            AVTID = data.getIntExtra("avatarchoseid", AVTID);
            imgInputAvatarE.setImageResource(AVTID);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
