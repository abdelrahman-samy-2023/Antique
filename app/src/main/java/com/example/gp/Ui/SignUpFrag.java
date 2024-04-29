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
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.example.gp.Models.ApiInterface;
import com.example.gp.Models.Authentication;
import com.example.gp.Models.PostClient;
import com.example.gp.R;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFrag extends Fragment {
    TextInputLayout t1, t2, t3, t4;
    AppCompatButton b;
    String name, email, password1, passwordconf,token;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    boolean isloggedin=false;
    String password ;
    String password12;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup group = (ViewGroup) inflater.inflate(R.layout.signup_fragment, container, false);
        b = group.findViewById(R.id.signup);
        t1 = group.findViewById(R.id.FullName);
        t2 = group.findViewById(R.id.email_et1);
        t3 = group.findViewById(R.id.pa_et1);
        t4 = group.findViewById(R.id.pa_et2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validation_email() | !validation_name() | !validation_password() |!validationConfpassword()){
                    return;
                }
                else
                {
                    reg();





                }
            }
        });



        reg();
        return group;

    }

    public void reg() {

                name = t1.getEditText().toString().trim();
                email = t2.getEditText().getText().toString().trim();
                password1 = t3.getEditText().getText().toString().trim();
                passwordconf = t4.getEditText().getText().toString().trim();
                preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                editor=preferences.edit();
                PostClient.getInstance().reg(name,email,password1,passwordconf).enqueue(new Callback<Authentication>() {
                    @Override
                    public void onResponse(Call<Authentication> call, Response<Authentication> response) {
                        if(response.isSuccessful()) {
                            Log.d("TAG", response.body().getData().getToken());
                            token = response.body().getData().getToken();
                            editor.putString("token",token);
                            isloggedin=true;
                            editor.putBoolean("isloggedin",isloggedin);
                            editor.apply();
                            startActivity(new Intent(getContext(),MainHostFragment.class));


                        }
                        else
                        {

                        }


                    }

                    @Override
                    public void onFailure(Call<Authentication> call, Throwable t) {
                        Log.d("TAG1", t.getMessage());
                        Toast.makeText(getContext(), "nnnnnnoneeeeeeee", Toast.LENGTH_SHORT).show();
                    }


                });
            }




    public boolean validation_email() {
        String email = t2.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            t2.setError("Field cant be empty");
            t2.requestFocus();
            return false;

        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            t2.setError("Enter Valid Email");
            t2.requestFocus();
            return false;
        } else {
            t2.setError(null);
            return true;

        }
    }

    public boolean validation_name() {
        String fullname = t1.getEditText().getText().toString().trim();
        if (fullname.isEmpty()) {
            t1.setError("Field cant be empty");
            t1.requestFocus();
            return false;

        } else if(fullname.length()>20)
        {  t1.setError("fullName to long");
            t1.requestFocus();
            return false;

        }
        else
        {
            t1.setError(null);
            return true;

        }
    }

    public boolean validation_password() {
         password = t3.getEditText().getText().toString().trim();

        if (password.isEmpty()) {
            t3.setError("Field cant be empty");
            t3.requestFocus();
            return false;

        }


        else if(password.length()<8)
        {  t3.setError("password must be at least 8 charachters");
            t3.requestFocus();
            return false;

        }
        else
        {
            t3.setError(null);
            return true;

        }

    }
    public boolean validationConfpassword() {
         password12= t4.getEditText().getText().toString().trim();

        if (password12.isEmpty()) {
            t4.setError("Field cant be empty");
            t4.requestFocus();
            return false;

        }

        if (!password.equals(password12)) {
            t4.setError("password doesnt match");
            t4.requestFocus();
            return false;

        }
        else if(password.length()<8)
        {  t4.setError("password must be at least 8 charachters");
            t4.requestFocus();
            return false;

        }
        else
        {
            t4.setError(null);
            return true;

        }

    }

}







