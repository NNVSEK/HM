package com.example.hospitalmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
EditText Emailx,passwordx;
Button signinx,signupx;
Intent i;
FirebaseAuth Fauth;
ProgressBar pb1x;
FirebaseAuth.AuthStateListener FauthStateListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Emailx = findViewById(R.id.email);
        passwordx = findViewById(R.id.password);
        signinx = findViewById(R.id.signin);
        signupx = findViewById(R.id.signup);
        pb1x = findViewById(R.id.pb1);
        Fauth = FirebaseAuth.getInstance();

        FauthStateListner = new FirebaseAuth.AuthStateListener() {


            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = Fauth.getCurrentUser();
                if(firebaseUser != null){
                    Toast.makeText(MainActivity.this, "logged in ", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, signup.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(MainActivity.this, "details please ", Toast.LENGTH_SHORT).show();
                }

            }
        };
        signinx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String email = Emailx.getText().toString().trim();
                    String password = passwordx.getText().toString().trim();

                    if(TextUtils.isEmpty(email)){
                        Emailx.setError("Need the email dude");
                        return;

                    }
                    if(TextUtils.isEmpty(password)){
                        passwordx.setError("Need the password dude");
                        return;
                    }

                    pb1x.setVisibility(View.VISIBLE);

                    Fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                       if(!task.isSuccessful()){
                           Intent i = new Intent(MainActivity.this, signup.class);
                           startActivity(i);
                       }
                       else{

                           Toast.makeText(MainActivity.this, "not  success ", Toast.LENGTH_SHORT).show();
                       }
                        }
                    });

            }
        });
        signupx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent i = new Intent(MainActivity.this, signup.class);
startActivity(i);
            }
        });
    }


    /*@Override
    protected void onStart() {
        super.onStart();
        Fauth.addAuthStateListener(FauthStateListner );
    }*/
}
