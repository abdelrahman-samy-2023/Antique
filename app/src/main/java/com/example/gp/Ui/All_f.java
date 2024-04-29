package com.example.gp.Ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.gp.AuctionModel.AuctionsDatum;
import com.example.gp.AuctionModel.Authentication1;
import com.example.gp.Models.PostClient;
import com.example.gp.Models.RVlLisetner;
import com.example.gp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class All_f extends Fragment {
    RecyclerView r;
    String token;
    HomeAdapter homeAdapter;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    List<AuctionsDatum> authentication1s;
    ProgressBar p;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public All_f() {
        // Required empty public constructor
    }



    public static All_f newInstance(String param1, String param2) {
        All_f fragment = new All_f();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         ViewGroup group= (ViewGroup)inflater.inflate(R.layout.fragment_all_f, container, false);
        r = group.findViewById(R.id.rv_all);
        p = group.findViewById(R.id.progressBar1);
        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = preferences.edit();
        authentication1s= new ArrayList<>();
        token = preferences.getString("token","A3A3A3");
        getAuction();
        return group;
    }

    public void getAuction()

    { p.setVisibility(View.VISIBLE);
        PostClient.getInstance().getAuction(token).enqueue(new Callback<Authentication1>() {
            @Override
            public void onResponse(Call<Authentication1> call, Response<Authentication1> response) {
                if(response.isSuccessful()) {
                    authentication1s.clear();
                    Authentication1 authentication1 = response.body();
                    authentication1s = new ArrayList<>(Arrays.asList(authentication1.getAuctionsData()));
                    HomeAdapter homeAdapter = new HomeAdapter(getContext(), new RVlLisetner() {
                        @Override
                        public void OnitemClicked(int id) {
                            Intent intent = new Intent(getContext(), ProductDetail.class);
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }
                    }, authentication1s);
                    r.setAdapter(homeAdapter);
                    p.setVisibility(View.GONE);
                    r.setHasFixedSize(true);
                    r.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    Log.d("TTTTTT", response.code() + "");
                }


            }

            @Override
            public void onFailure(Call<Authentication1> call, Throwable t) {
                {
                    Log.d("yuyuyuy",t.getMessage());
                    p.setVisibility(View.GONE);
                }

            }
        });
    }
}