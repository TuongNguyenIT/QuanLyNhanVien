package com.iter.tuongnguyen.cta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.AvatarAdapter;

public class ChoseAvatarActivity extends AppCompatActivity {
    private GridView gvAvatar;
    private ArrayList arrAvatar;
    private AvatarAdapter avatarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_avatar);
        addControls();
        addEvents();
    }

    private void addEvents() {
        gvAvatar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();
                intent.putExtra("avatachose", (int)arrAvatar.get(position));
                intent.putExtra("avatarchoseid", (int)arrAvatar.get(position));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void addControls() {
        gvAvatar = findViewById(R.id.gvChoseAvatar);
        arrAvatar = new ArrayList();
        arrAvatar.add(R.drawable.avatar1);
        arrAvatar.add(R.drawable.avatar2);
        arrAvatar.add(R.drawable.avatar3);
        arrAvatar.add(R.drawable.avatar4);
        arrAvatar.add(R.drawable.avatar5);
        arrAvatar.add(R.drawable.avatar6);
        arrAvatar.add(R.drawable.avatar7);
        arrAvatar.add(R.drawable.avatar8);
        arrAvatar.add(R.drawable.avatar9);
        arrAvatar.add(R.drawable.avatar10);
        arrAvatar.add(R.drawable.avatar12);
        arrAvatar.add(R.drawable.avatar13);
        avatarAdapter = new AvatarAdapter(ChoseAvatarActivity.this, R.layout.avatar_item,arrAvatar);
        gvAvatar.setAdapter(avatarAdapter);
    }

}
