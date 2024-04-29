package com.example.gp.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.example.gp.Models.LoginAdapter;
import com.example.gp.R;
import com.google.android.material.tabs.TabLayout;

public class LoginActivty extends AppCompatActivity {
    ViewPager pager;
    TabLayout tabLayout;
    float v= 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activty);
        tabLayout = findViewById(R.id.tab);
        pager = findViewById(R.id.pager);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("SignUp"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTranslationY(300);
        tabLayout.setAlpha(v);
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
    }
}