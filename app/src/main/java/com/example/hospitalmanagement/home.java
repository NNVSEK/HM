package com.example.hospitalmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {
Button patientlistx,profilex,settingsx,addpatientx,reportsx,paymentx;
Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        profilex = findViewById(R.id.profile);
        patientlistx = findViewById(R.id.patientlist);
        settingsx = findViewById(R.id.settings);
        addpatientx = findViewById(R.id.addpatient);
        reportsx = findViewById(R.id.reports);
        paymentx = findViewById(R.id.payment);


        profilex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this,profile.class);
                startActivity(i);

            }
        });

        patientlistx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this,patientlist.class);
                startActivity(i);

            }
        });
        settingsx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this,settings.class);
                startActivity(i);
            }
        });
        addpatientx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish ();
                Intent i = new Intent(home.this,addpatient.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        reportsx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this,reports.class);
                startActivity(i);
            }
        });
        paymentx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(home.this,payment.class);
                startActivity(i);

            }
        });
    }


}
