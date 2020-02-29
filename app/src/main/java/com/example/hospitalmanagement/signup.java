package com.example.hospitalmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
TextView t1;
EditText usernamex,passwordx,emailx,phonex;
Button registerx,back;
FirebaseAuth FAuth;
Intent i,i1;
ProgressBar pb2x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        back = findViewById(R.id.backtosignin);
        usernamex = findViewById(R.id.username);
        passwordx = findViewById(R.id.password);
        emailx =  findViewById(R.id.email);
        phonex = findViewById(R.id.phone);
        registerx =        findViewById(R.id.register);
        pb2x = findViewById(R.id.pb2);
        FAuth = FirebaseAuth.getInstance();

        if(FAuth.getCurrentUser() !=null){
            Intent i1 = new Intent(signup.this,home.class);
            startActivity(i1);
        }

        registerx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailx.getText().toString().trim();
                String password = passwordx.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    emailx.setError("Need the email dude");
                    return;

                }
                if(TextUtils.isEmpty(password)){
                    passwordx.setError("Need the password dude");
                    return;
                }

            pb2x.setVisibility(View.VISIBLE);

                FAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()){
                             Toast.makeText(signup.this, "done", Toast.LENGTH_SHORT).show();
                         Intent i1 = new Intent(signup.this,home.class);
                         startActivity(i1);

                         }
                         else {
                             Toast.makeText(signup.this,"ayyayyo",Toast.LENGTH_SHORT ).show();
                         }
                    }
                });
            }

        });
     back.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent i = new Intent(signup.this, MainActivity.class);
             startActivity(i);
         }
     });
    }
}
