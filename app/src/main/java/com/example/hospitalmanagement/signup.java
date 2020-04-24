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

public class signup extends AppCompatActivity  implements View.OnClickListener {

    EditText txtName, txtAddress, txtPassword, txtEmail, txtPhone;
    TextView lblLogin;
    Button btnSignUp;
    Intent intent;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    DataServices service;

    boolean isLogin;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txtName = findViewById(R.id.txtNameSignUp);
        txtAddress = findViewById(R.id.txtAddressSignUp);
        txtEmail = findViewById(R.id.txtEmailSignUp);
        txtPhone = findViewById(R.id.txtPhoneSignUp);
        txtPassword = findViewById(R.id.txtPasswordSignUp);
        lblLogin = findViewById(R.id.lblLogin);
        btnSignUp = findViewById(R.id.btnRegister);

        btnSignUp.setOnClickListener(this);
        lblLogin.setOnClickListener(this);

        sp = getSharedPreferences(MyVariables.cacheFile, Context.MODE_PRIVATE);

        isLogin = sp.getBoolean(MyVariables.keyLoginAuth, MyVariables.defaultLoginAuth);
        userID = sp.getInt(MyVariables.keyUserID, MyVariables.defaultUserID);

        if (isLogin && userID > 0) {
            finish();
            intent = new Intent(getApplicationContext(), home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegister) {
            btnSignUpClick(v);
        } else if (v.getId() == R.id.lblLogin) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }

    private void btnSignUpClick(View v) {

        final String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        final String name = txtName.getText().toString();
        final String address = txtAddress.getText().toString();
        final String phone = txtPhone.getText().toString();

        if (TextUtils.isEmpty(email)) {
            txtEmail.setError("Email is Required.");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            txtPassword.setError("Password is Required.");
            return;
        }

        if (password.length() < 6) {
            txtPassword.setError("Password Must be >= 6 Characters");
            return;
        }

        LoginDetails loginDetails = new LoginDetails(email, password);
        loginDetails.setUser(new UserDetails(name, email, phone, address));

        Toast.makeText ( getApplicationContext ( ), "registering", Toast.LENGTH_SHORT ).show ( );

        service = RetrofitClientInstance.getRetrofitInstance().create(DataServices.class);

        Call<UserDetails> call = service.executeSignUp(loginDetails);

        call.enqueue(new Callback<UserDetails> () {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext (), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                UserDetails userDetails = response.body();

                if (userDetails.getStr().equals("Already registered")) {
                    Toast.makeText(getApplicationContext (), "This email is already registered.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext (), userDetails.getStr(), Toast.LENGTH_SHORT).show();

                editor = sp.edit();
                //store values
                editor.putBoolean(MyVariables.keyLoginAuth, true);
                editor.putInt(MyVariables.keyUserID, userDetails.getUserID());
                editor.apply();

                finish();
                Intent intent = new Intent(getApplicationContext(), home.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                Toast.makeText(getApplicationContext (), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}


