package com.example.hospitalmanagement;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin;
    EditText txtEmail, txtPassword;
    TextView lblRegister;
    Intent intent;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    DataServices service;

    boolean isLogin;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        lblRegister=findViewById ( R.id.lblRegister );
        btnLogin=findViewById ( R.id.btnLogin );
        txtEmail=findViewById ( R.id.txtUserNameLogin );
        txtPassword=findViewById ( R.id.txtPasswordLogin );
        btnLogin.setOnClickListener ( (View.OnClickListener) this );

        lblRegister.setOnClickListener ( (View.OnClickListener) this );

        //directs the compiler to the cache file
        sp=getSharedPreferences ( MyVariables.cacheFile, Context.MODE_PRIVATE );

        //get values from cache file
        isLogin=sp.getBoolean ( MyVariables.keyLoginAuth, MyVariables.defaultLoginAuth );
        userID=sp.getInt ( MyVariables.keyUserID, MyVariables.defaultUserID );
        //check if values are valid.
        if (isLogin && userID > 0) {
            finish ( );
            intent=new Intent ( getApplicationContext ( ), home.class );    //logs the user without credentials
            intent.addFlags ( Intent.FLAG_ACTIVITY_CLEAR_TOP );
            startActivity ( intent );
        }

    }

    @Override
    protected void onStart() {
        super.onStart ( );
    }

    @Override
    public void onClick(View v) {

        switch (v.getId ( )) {
            case R.id.btnLogin:
                btnLoginClick ( v );
                break;
            case R.id.lblRegister:
                intent=new Intent ( MainActivity.this, signup.class );
                startActivity ( intent );
                break;
            default:
                Toast.makeText ( this, "Invalid click operation!", Toast.LENGTH_SHORT ).show ( );
                break;
        }
    }

    private void btnLoginClick(View v) {

        String email=txtEmail.getText ( ).toString ( ).trim ( );
        String password=txtPassword.getText ( ).toString ( ).trim ( );

        if (TextUtils.isEmpty ( email )) {
            txtEmail.setError ( "Email is Required." );
            return;
        }

        if (TextUtils.isEmpty ( password )) {
            txtPassword.setError ( "Password is Required." );
            return;
        }

        if (password.length ( ) < 6) {
            txtPassword.setError ( "Password Must be >= 6 Characters" );
            return;
        }

        LoginDetails loginDetails=new LoginDetails ( email, password );

        service=RetrofitClientInstance.getRetrofitInstance ( ).create ( DataServices.class );

        Call<UserDetails> call=service.executeLogin ( loginDetails );

        call.enqueue ( new Callback<UserDetails> ( ) {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                if (!response.isSuccessful ( )) {
                    Toast.makeText ( MainActivity.this, response.code ( ), Toast.LENGTH_SHORT ).show ( );
                    return;
                }

                UserDetails userDetails=response.body ( );

                if (!userDetails.getStr ( ).equals ( "Valid" )) {
                    Toast.makeText ( MainActivity.this, userDetails.getStr ( ), Toast.LENGTH_SHORT ).show ( );
                    return;
                }

                //Successful log in
                editor=sp.edit ( );
                //store values
                editor.putBoolean ( MyVariables.keyLoginAuth, true );
                editor.putInt ( MyVariables.keyUserID, userDetails.getUserID ( ) );
                editor.apply ( );

                finish ( );
                intent=new Intent ( getApplicationContext ( ), home.class );
                intent.addFlags ( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                startActivity ( intent );
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                Toast.makeText ( MainActivity.this, t.getMessage ( ), Toast.LENGTH_SHORT ).show ( );
            }
        } );


    }
}
