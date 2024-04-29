package com.example.gp.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.gp.GetProd.AuctionProduct;
import com.example.gp.GetProd.Authentication3;
import com.example.gp.Models.PostClient;
import com.example.gp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Product_bid extends AppCompatActivity {
    ImageView iv;
    TextView t1,t2;
    List<SlideModel> list;
    AuctionProduct auctionProduct,auctionProduct2,auctionProduct3;
    String token;
    int Id;
    SharedPreferences preferences;
    List<AuctionProduct> authentication3s;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_bid);
        iv = findViewById(R.id.iv55);
        t1 = findViewById(R.id.nameauc);
        t2 = findViewById(R.id.textView12);
        Intent intent = getIntent();
        id= intent.getIntExtra("id",-1);
        Id = intent.getIntExtra("Id",-1);
        Log.d("ayyyyyyyyyyyy",id+"");
        preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
         token = preferences.getString("token", "mknmkg50");
        authentication3s = new ArrayList<>();
        getProd();

    }

    public  void getProd()
    {
        PostClient.getInstance().getOneProduct(token,id).enqueue(new Callback<Authentication3>() {
            @Override
            public void onResponse(Call<Authentication3> call, Response<Authentication3> response) {
                Authentication3 authentication3 =response.body();
                authentication3s = new ArrayList<>(Arrays.asList(authentication3.getAuctionProducts()));
                auctionProduct = authentication3s.get(0);
                auctionProduct2 = authentication3s.get(1);
                auctionProduct3 = authentication3s.get(2);

                if(Id==1) {
                    Log.d("hellllllllo0", Id + "");
                    Log.d("jjjjjjjj",auctionProduct.getName());
                    t1.setText(auctionProduct.getName());
                    t2.setText(auctionProduct.getDescription());
                    iv.setImageResource(R.drawable.n11);

                }
               else if(Id==2) {
                        Log.d("hellllllllo0", Id + "");
                        Log.d("jjjjjjjj",auctionProduct2.getName());
                        t1.setText(auctionProduct2.getName());
                        t2.setText(auctionProduct2.getDescription());
                        iv.setImageResource(R.drawable.n22);

                    }

                else if(Id==3) {
                    Log.d("hellllllllo0", Id + "");
                    Log.d("jjjjjjjj",auctionProduct3.getName());
                    t1.setText(auctionProduct3.getName());
                    t2.setText(auctionProduct3.getDescription());
                    iv.setImageResource(R.drawable.n33);

                }


            }

            @Override
            public void onFailure(Call<Authentication3> call, Throwable t) {

            }
        });

    }
}