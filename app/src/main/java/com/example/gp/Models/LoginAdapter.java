package com.example.gp.Models;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.gp.Ui.LoginFrag;
import com.example.gp.Ui.SignUpFrag;


public class LoginAdapter extends FragmentPagerAdapter {
    Context c;
    int tolltab;
    public LoginAdapter(@NonNull FragmentManager fm, Context c,int tolltab) {
        super(fm);
        this.c=c;
        this.tolltab=tolltab;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                LoginFrag loginFrag = new LoginFrag();
                return loginFrag;
            case 1:
                SignUpFrag signUpFrag = new SignUpFrag();
                return  signUpFrag;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tolltab;
    }
}
