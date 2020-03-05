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

public class signup extends AppCompatActivity implements View.OnClickListener {
TextView t1;
EditText usernamex,passwordx,emailx,phonex;
Button registerx,back;
FirebaseAuth FAuth;

ProgressBar pb2x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        back = findViewById(R.id.backtosignin);
        usernamex = findViewById(R.id.username);
        passwordx = findViewById(R.id.password);
        emailx = findViewById(R.id.email);
        phonex = findViewById(R.id.phone);
        registerx = findViewById(R.id.register);
        pb2x = findViewById(R.id.pb2);
        FAuth = FirebaseAuth.getInstance();
registerx.setOnClickListener(this);
back.setOnClickListener(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(signup.this, MainActivity.class);
                startActivity(i);
            }
        });
    }



    @Override
    public void onClick(View v) {
if (v == registerx){
    registeruser();
}
    }

    private void registeruser() {
        String username = usernamex.getText().toString().trim();
        String email = emailx.getText().toString().trim();
        String password = passwordx.getText().toString().trim();
        String phone = phonex.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
           Toast.makeText(this,"Enter email",Toast.LENGTH_SHORT).show();
           return;
    }
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"Enter username",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Enter phone",Toast.LENGTH_SHORT).show();
            return;
        }
FAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
Toast.makeText(signup.this,"Registered user", Toast.LENGTH_SHORT).show();
Intent i1 = new Intent(signup.this,MainActivity.class);
startActivity(i1);
return;
        }
        else {
Toast.makeText(signup.this,"something is starting to go wrong", Toast.LENGTH_SHORT).show();
return;
        }
    }
});

    }

}


