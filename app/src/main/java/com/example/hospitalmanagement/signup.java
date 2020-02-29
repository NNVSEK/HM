package com.example.hospitalmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class signup extends AppCompatActivity {
TextView t1,t2;
EditText usernamex,passwordx,emailx,phonex;
Button registerx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernamex = findViewById(R.id.username);
        passwordx = findViewById(R.id.password);
        emailx =  findViewById(R.id.email);
        phonex = findViewById(R.id.phone);
        registerx =        findViewById(R.id.register);

        registerx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
