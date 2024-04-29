package com.example.gp.Ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.gp.Models.Authentication;
import com.example.gp.Models.PostClient;
import com.example.gp.R;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFrag extends Fragment {
    TextInputLayout t1,t2;
    TextView t3;
    String email,password,token,name;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    AppCompatButton b;
    private ProgressBar pb1;
    boolean isloggedin=false;
    float v=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup group=(ViewGroup)inflater.inflate(R.layout.login_fragment, container, false);
        t1 = group.findViewById(R.id.email_et);
        t2 = group.findViewById(R.id.pa_et);
        t3 = group.findViewById(R.id.forget);
        b= group.findViewById(R.id.Login);
        pb1= group.findViewById(R.id.progressBar2);
        t1.setTranslationX(800);
        t2.setTranslationX(800);
        t3.setTranslationX(800);
        b.setTranslationX(800);
        t1.setAlpha(v);
        t2.setAlpha(v);
        b.setAlpha(v);
        t1.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        t2.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        t3.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        b.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !validation_email() | !validation_password() )
                {
                    return;
                }
                else
                {
                    login();

                }
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ResetPass.class));

            }
        });
        return group;
    }

    public void login()
    {
            email = t1.getEditText().getText().toString().trim();
            password = t2.getEditText().getText().toString().trim();
            pb1.setVisibility(View.VISIBLE);
            PostClient.getInstance().login(email,password).enqueue(new Callback<Authentication>() {
                @Override
                public void onResponse(Call<Authentication> call, Response<Authentication> response) {
                    if(response.isSuccessful()) {
                        token  = response.body().getData().getToken();
                        name = response.body().getData().getName();
                        email = response.body().getData().getEmail();
                        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                        editor=preferences.edit();
                        editor.putString("token",token);
                        editor.putString("email",email);
                        editor.putString("name",name);
                        Log.d("Taggg",token);
                        isloggedin=true;
                        editor.putBoolean("isloggedin",isloggedin);
                        editor.apply();
                        startActivity(new Intent(getContext(),MainHostFragment.class));
                        pb1.setVisibility(View.GONE);


                    }
                    else
                    {
                        Toast.makeText(getContext(), "doneeeeeeee",Toast.LENGTH_SHORT);
                        Log.d("tttta",response.code()+"");
                        pb1.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onFailure(Call<Authentication> call, Throwable t) {
                    Log.d("TAG3", t.getMessage());
                    Toast.makeText(getContext(), "nnnnnnoneeeeeeee", Toast.LENGTH_SHORT).show();
                    pb1.setVisibility(View.GONE);

                }


    });


    }
    public boolean validation_password() {
        String password = t2.getEditText().getText().toString().trim();

        if (password.isEmpty()) {
            t2.setError("Field cant be empty");
            t2.requestFocus();
            return false;

        } else if (password.length() < 8) {
            t2.setError("password must be at least 8 charachters");
            t2.requestFocus();
            return false;

        } else {
            t2.setError(null);
            return true;

        }
    }

    public boolean validation_email() {
        String email = t1.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            t1.setError("Field cant be empty");
            t1.requestFocus();
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            t1.setError("Enter Valid Email");
            t1.requestFocus();
            return false;
        } else {
            t1.setError(null);
            return true;

        }
    }
}
