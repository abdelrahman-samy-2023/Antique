package com.example.gp.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.example.gp.R;

public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
   boolean loggedin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

                loggedin=preferences.getBoolean("isloggedin",false);
               if(loggedin==true)
                {
                    startActivity(new Intent(getApplicationContext(),MainHostFragment.class));

                }
               else
               {
                   startActivity(new Intent(getApplicationContext(),LoginActivty.class));
               }


            }
        },2000);
    }
}