package com.example.hospitalmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;

public class addpatient extends AppCompatActivity {
    EditText ename, eemail, edob, ephone, eage, ebloodgroup;
    Button show, save;
    Intent i;
    DataServices service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_addpatient );
        ename=findViewById ( R.id.ename );
        eemail=findViewById ( R.id.eemail );
        edob=findViewById ( R.id.edob );
        ephone=findViewById ( R.id.ephone );
        eage=findViewById ( R.id.eage );
        ebloodgroup=findViewById ( R.id.ebloodgroup );
        save=findViewById ( R.id.save );

        save.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                final String name = ename.getText().toString().trim();
                String email = eemail.getText().toString().trim();
                final String dob = edob.getText().toString();
                final String phone = ephone.getText().toString();
                final String age = eage.getText().toString();
                final String bloodgroup = ebloodgroup.getText().toString();

                LoginDetails loginDetails  = new LoginDetails(name, email);
                loginDetails.setUser(new UserDetails(name, email,dob, phone, age,bloodgroup));
                Toast.makeText ( getApplicationContext ( ), "saving", Toast.LENGTH_SHORT ).show ( );
               // service = RetrofitClientInstance.getRetrofitInstance().create(DataServices.class);
               // Call<UserDetails> call = service.executeSignUp(loginDetails);
            }

        } );

    }

}

