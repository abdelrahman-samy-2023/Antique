package com.example.gp.Models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.gp.R;

import java.util.ArrayList;

public class ImageAdapter extends PagerAdapter {
    Context c;
   Integer [] imag = {R.drawable.n11,R.drawable.n22,R.drawable.n33};
   INTENTT intentt;

    public ImageAdapter(Context c, INTENTT intentt) {
        this.c = c;
        this.intentt = intentt;
    }

    @Override
    public int getCount() {
        return imag.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
     View view = LayoutInflater.from(c).inflate(R.layout.viewpager_image_adapter,null);
     container.addView(view,0);
     ImageView imageView = view.findViewById(R.id.iv_adapter);
      imageView.setImageResource(imag[position]);
      view.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(position==0)
              intentt.OnitemClicked1(1);
              else if(position==1)
                  intentt.OnitemClicked1(2);
              else if(position==2)
                  intentt.OnitemClicked1(3);
              else
                  Toast.makeText(c, "im in 3", Toast.LENGTH_SHORT).show();
          }
      });
        ViewPager viewPager = (ViewPager)container;
        viewPager.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager)container;
      View view = (View)object;
      viewPager.removeView(view);
    }
}
