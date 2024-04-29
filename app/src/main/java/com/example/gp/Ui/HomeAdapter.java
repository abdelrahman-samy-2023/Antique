package com.example.gp.Ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gp.AuctionModel.AuctionsDatum;
import com.example.gp.AuctionModel.Authentication1;
import com.example.gp.Models.RVlLisetner;
import com.example.gp.R;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.adapterHolder> {
    Context c;
    RVlLisetner rvLisetner;
    int id;
    private List<AuctionsDatum> ho = new ArrayList<>();



    public HomeAdapter(Context c, RVlLisetner rvLisetner, List<AuctionsDatum> ho) {
        this.c = c;
        this.rvLisetner = rvLisetner;
        this.ho = ho;
    }

    public HomeAdapter(Context c) {
        this.c = c;
    }

    @NonNull
    @Override
    public adapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new adapterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull adapterHolder holder, int position) {

        AuctionsDatum authentication1 = ho.get(position);
        holder.t1.setText(authentication1.getVendor().getName());
        holder.t2.setText(authentication1.getStartAt());
        holder.t4.setText(authentication1.getName());
        holder.t3.setText(authentication1.getEndAt());
        id=authentication1.getId();



    }

    @Override
    public int getItemCount() {
        return ho.size();
    }

    public class adapterHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4,t5;



        public adapterHolder(@NonNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.textView7);
            t2 = itemView.findViewById(R.id.textView14);
            t3 = itemView.findViewById(R.id.textView20);
            t4 = itemView.findViewById(R.id.textView10);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    rvLisetner.OnitemClicked(id);
                }
            });

        }

    }
}
