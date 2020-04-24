package com.example.hospitalmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addpatient extends AppCompatActivity {
    EditText ename, eemail, edob, ephone, eage, ebloodgroup;
    Button show, save;
    Intent i;
    DataServices service;
    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_addpatient );
        ename=findViewById ( R.id.enamePatient );
        eemail=findViewById ( R.id.eemailPatient );
        edob=findViewById ( R.id.edobPatient );
        ephone=findViewById ( R.id.ephonePatient );
        eage=findViewById ( R.id.eagePatient );
        ebloodgroup=findViewById ( R.id.ebloodgroupPatient );
        save=findViewById ( R.id.btnSavePatient );

        save.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                final String name=ename.getText ( ).toString ( ).trim ( );
                String email=eemail.getText ( ).toString ( ).trim ( );
                final Date dob=new Date ( );   // for now leave it like this!!
                final String phone=ephone.getText ( ).toString ( );
                final int age=0;    //for now
                final String bloodGroup=ebloodgroup.getText ( ).toString ( );

                patient=new Patient (
                        name, phone, age, email, bloodGroup
                );

                addPatient ( );
            }

        } );

    }

    private void addPatient() {
        service=RetrofitClientInstance.getRetrofitInstance ( ).create ( DataServices.class );
        Call<String> call=service.executeAddPatient ( patient );
        call.enqueue ( new Callback<String> ( ) {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful ( )) {
                    Toast.makeText ( addpatient.this, response.message (), Toast.LENGTH_SHORT ).show ( );
                    return;
                }
                Toast.makeText ( getApplicationContext ( ), "ARSH", Toast.LENGTH_LONG ).show ( );

                //if query is successful
                if (response.body ( ).equals ( "success" )) {
                    Toast.makeText ( addpatient.this, "Successfully added", Toast.LENGTH_SHORT ).show ( );
                } else {
                    Toast.makeText ( addpatient.this, "Something went wrong!!", Toast.LENGTH_SHORT ).show ( );
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText ( addpatient.this, t.getMessage ( ), Toast.LENGTH_SHORT ).show ( );
            }
        } );
    }

}

