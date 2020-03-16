package com.example.hospitalmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class settings extends AppCompatActivity {
Button logoutx,showx;
FirebaseAuth Fauth;
TextView namex,phonex,emailx,dobx,bloodgroupx,agex;
EditText enamex,ephonex,eemailx,edobx,ebloodgroupx,eagex;
DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        namex = findViewById(R.id.name);
        phonex = findViewById(R.id.phone);
        emailx = findViewById(R.id.email);
        dobx = findViewById(R.id.dob);
        bloodgroupx = findViewById(R.id.blodgroup);
        agex = findViewById(R.id.age);
        enamex = findViewById(R.id.ename);
        ephonex = findViewById(R.id.ephone);
        eemailx = findViewById(R.id.eemail);
        edobx = findViewById(R.id.edob);
        ebloodgroupx = findViewById(R.id.ebloodgroup);
        eagex = findViewById(R.id.eage);
        showx = findViewById(R.id.show);

        Fauth = FirebaseAuth.getInstance();

        logoutx= findViewById(R.id.logout);

        showx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
reff = FirebaseDatabase.getInstance().getReference().child("profile").child("nnvsek");
reff.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
   String name = dataSnapshot.child("name").getValue().toString();
   String phone = dataSnapshot.child("phone").getValue().toString();
   String email = dataSnapshot.child("email").getValue().toString();
   String dob = dataSnapshot.child("dob").getValue().toString();
   String bloodgroup =  dataSnapshot.child("bloodgroup").getValue().toString();
   String age = dataSnapshot.child("age").getValue().toString();
   enamex.setText(name);
   ephonex.setText(phone);
   eemailx.setText(email);
   edobx.setText(dob);
   ebloodgroupx.setText(bloodgroup);
   eagex.setText(age);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
            }
        });
        logoutx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fauth.signOut();
                finish();
                startActivity(new Intent(settings.this, MainActivity.class) );

            }
        });
    }
}
