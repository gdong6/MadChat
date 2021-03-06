package com.example.madchat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.madchat.ui.login.LoginActivity;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private NavigationBarView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnItemSelectedListener(bottomnavFunction);

        //仅用于Login部分测试，可以删掉==========
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        //===================================

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new Chat()).commit(); //打开的第一个界面
    }

    private NavigationBarView.OnItemSelectedListener bottomnavFunction = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment fragment = null;
            switch(item.getItemId()){
                case R.id.chatIcon:
                    fragment = new Chat();
                    break;
                case R.id.homeIcon:
                    fragment = new Home();
                    break;
                case R.id.meIcon:
                    fragment = new Me();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            return true;
        }
    };


}