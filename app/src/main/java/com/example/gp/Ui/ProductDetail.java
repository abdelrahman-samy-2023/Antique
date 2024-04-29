package com.example.gp.Ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import com.example.gp.AuctionModel.AuctionsDatum;
import com.example.gp.AuctionModel.Authentication1;
import com.example.gp.GetProd.AuctionProduct;
import com.example.gp.GetProd.Authentication3;
import com.example.gp.Models.INTENTT;
import com.example.gp.Models.ImageAdapter;
import com.example.gp.Models.PostClient;
import com.example.gp.R;
import com.example.gp.SingleAuctionModel.Authentication2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetail extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6;
    TextView tt;
    int Id;
    SharedPreferences preferences;
    int id;
    SharedPreferences.Editor editor;
    List<Authentication2> authentication2s;
    List<AuctionProduct> authentication3s;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        t1 = findViewById(R.id.textView4);
        t2 = findViewById(R.id.textView6);
        t3 = findViewById(R.id.textView22);
        t4 = findViewById(R.id.textView24);
        t5 = findViewById(R.id.tvb1);
        t6 = findViewById(R.id.tvb2);
        ViewPager viewPager = findViewById(R.id.pa);
        ImageAdapter adapter = new ImageAdapter(this, new INTENTT() {
            @Override
            public void OnitemClicked1(int Id ) {
                Intent intent = new Intent(getBaseContext(), Product_bid.class);
                intent.putExtra("id",id);
                intent.putExtra("Id",Id);
                startActivity(intent);


            }
        });
        viewPager.setPadding(100,0,100,0);
        viewPager.setAdapter(adapter);
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        token=   preferences.getString("token","mknmkg50");
        Intent intent = getIntent();
        id= intent.getIntExtra("id",-1);
        authentication2s = new ArrayList<>();
        authentication3s = new ArrayList<>();



        getOneAuction();

    }
    public void getOneAuction(){
        Log.d("idddddddddddddd",id+"");
        Log.d("tttttttttttttttt",token);
        PostClient.getInstance().getOneAuction(token,id).enqueue(new Callback<Authentication2>() {
            @Override
            public void onResponse(Call<Authentication2> call, Response<Authentication2> response) {
                if(response.isSuccessful())
                {
                    t1.setText(response.body().getSingleAuction().getName());
                    t2.setText(response.body().getSingleAuction().getDescription());
                    t3.setText(response.body().getSingleAuction().getStartPrice()+"");
                    t4.setText(response.body().getSingleAuction().getBiddingPrice()+"");
                    t5.setText(response.body().getSingleAuction().getStartAt()+"");
                    t6.setText(response.body().getSingleAuction().getEndAt()+"");

                }
                else {
                    Log.d("idddddddddddddd",response.code()+"");
                }

            }

            @Override
            public void onFailure(Call<Authentication2> call, Throwable t) {
                Log.d("badawyyyyy",t.getMessage());

            }
        });
    }

    public boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
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


}