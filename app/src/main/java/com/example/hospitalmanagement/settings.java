package com.example.hospitalmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class settings extends AppCompatActivity implements View.OnClickListener {
    Button logout;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_settings );
        logout=findViewById ( R.id.logout );
        logout.setOnClickListener ( this );

        sp=getSharedPreferences ( MyVariables.cacheFile, Context.MODE_PRIVATE );

/*logout.setOnClickListener ( new View.OnClickListener ( ) {
    @Override
    public void onClick(View v) {
        Intent i = new Intent(settings.this,MainActivity.class);
        startActivity(i);
    }
} );*/


    }

    @Override
    public void onClick(View v) {
        if (v.getId ( ) == R.id.logout) {
            editor=sp.edit ( );
            editor.putBoolean ( MyVariables.keyLoginAuth, false );
            editor.putInt ( MyVariables.keyUserID, 0 );
            editor.apply ( );

            finish ( );
            startActivity ( new Intent ( settings.this, MainActivity.class ) );
        }
    }
}
