package com.example.gp.Ui;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.gp.Models.Authentication;
import com.example.gp.Models.PostClient;
import com.example.gp.R;
import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainHostFragment extends AppCompatActivity {
    DrawerLayout d;
    ChipNavigationBar b1;
    NavigationView n;
    boolean loggedin;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    String token;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_host_fragment);
        b1 = findViewById(R.id.boo);
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
         token = preferences.getString("token", "mknmkg50");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new HomeFrag()).commit();
        b1.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment f = null;
                switch (i) {
                    case R.id.Home:
                        f = new HomeFrag();
                        break;
                    case R.id.Cart:
                        f = new Cart();
                        break;
                    case R.id.logout:
                        logout();
                    case R.id.profile:
                        f = new MyProfile();
                        break;


                }
                if (f != null)
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment, f).commit();

            }
        });
        if (!isConnected(MainHostFragment.this)) {
            buildDialog(MainHostFragment.this).show();


        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(!isConnected(MainHostFragment.this))
        {
            buildDialog(MainHostFragment.this).show();


        }


    }


    public boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {
        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");

        builder.setMessage("You need to have Mobile Data or wifi to access this");

        return builder;
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Are you sure you want to close this Application?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)

                    {
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
    public void logout()
    {
        PostClient.getInstance().logout(token).enqueue(new Callback<Authentication>() {
            @Override
            public void onResponse(Call<Authentication> call, Response<Authentication> response) {

                if(response.isSuccessful())
                {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.remove("token");
                    editor.putBoolean("isloggedin",false);
                    editor.apply();

                    startActivity(new Intent(getApplicationContext(),LoginActivty.class));
                    Log.d("logouttttttt",response.code()+"");
                }



            }

            @Override
            public void onFailure(Call<Authentication> call, Throwable t) {

            }
        });

    }

}
