package com.iter.tuongnguyen.cta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import nhanvien.NhanVien;

public class AddActivity extends AppCompatActivity  {
    EditText txtInputName, txtInputID, txtLuong;
    ImageView imgInputAvatar;
    Button btnXacNhan;
    int avatarChoseId = R.drawable.avatar1;
    private static final  int RQCCA = 911;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        addControls();
        addEvents();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == RQCCA && resultCode == RESULT_OK && data != null){
            avatarChoseId = data.getIntExtra("avatachose", R.drawable.avatar13);
            imgInputAvatar.setImageResource(avatarChoseId);

        }else{
            Toast.makeText(this, "Có lỗi xảy ra!", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addEvents() {
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtInputID.getText().toString().length()==0 || txtInputName.getText().toString().length()==0 ||txtLuong.getText().toString().length()==0){
                    Toast.makeText(AddActivity.this, "Vui lòng nhập đủ thông tin trước khi xác nhận!", Toast.LENGTH_SHORT).show();
                    return;
                }
                NhanVien nhanVien = new NhanVien();
                nhanVien.setTen(txtInputName.getText().toString());
                nhanVien.setId(txtInputID.getText().toString());
                nhanVien.setHinh(avatarChoseId);
                nhanVien.setLuong(Integer.parseInt(txtLuong.getText().toString()));
                Intent intent = getIntent();
                intent.putExtra("nhanvienresult", nhanVien);
                setResult(RESULT_OK, intent);

                Intent intent1 = new Intent();
                finish();
            }
        });
        imgInputAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,ChoseAvatarActivity.class);
                startActivityForResult(intent,RQCCA);
            }
        });

    }

    private void addControls() {
        txtInputName = findViewById(R.id.txtInputName);
        txtInputID = findViewById(R.id.txtInputID);
        txtLuong = findViewById(R.id.txtSalary);
        imgInputAvatar = findViewById(R.id.imgInputAvatar);
        btnXacNhan = findViewById(R.id.btnXacNhan);

    }


}
