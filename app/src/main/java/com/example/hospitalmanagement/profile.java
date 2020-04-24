package com.example.hospitalmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class profile extends AppCompatActivity  {
TextView name,email,dob,phone,age,bloodgroup;
EditText ename,eemail,edob,ephone,eage,ebloodgroup;
Button show,save;
Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ename = findViewById ( R.id.ename );
        eemail = findViewById ( R.id.eemail);
        edob = findViewById ( R.id.edob);
        ephone = findViewById ( R.id.ephone );
        eage = findViewById ( R.id.eage );
        ebloodgroup = findViewById ( R.id.ebloodgroup );
        show = findViewById ( R.id.show );
save = findViewById ( R.id.save );
        show.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
              //  startActivity(new Intent(getApplicationContext(),Signup.class));

            }
        } );
    }}
