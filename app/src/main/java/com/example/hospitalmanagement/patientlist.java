package com.example.hospitalmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class patientlist extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientlist);
        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);

        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                JsonObjectRequest request = new JsonObjectRequest ( Request.Method.GET, "http://localhost/node-20200423T235533Z-001/node/patientlist.json", null,
                        new Response.Listener<JSONObject> ( ) {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray=response.getJSONArray ( "patients_list" );

                                    for (int i=0; i < jsonArray.length ( ); i++) {
                                        JSONObject patients_list=jsonArray.getJSONObject ( i );

                                        String firstName=patients_list.getString ( "firstname" );
                                        int age=patients_list.getInt ( "age" );
                                        String mail=patients_list.getString ( "mail" );
                                        String phone=patients_list.getString("phone");

                                        mTextViewResult.append ( firstName    + ",   " + String.valueOf ( age ) + ",   " + mail +","+ String.valueOf ( phone ) + "\n\n" );
                                    }
                                } catch (JSONException e) {

                                    e.printStackTrace ( );
                                }
                            }
                        }, new Response.ErrorListener ( )

                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e ( "VOLLEY","ERROR");
                        error.printStackTrace();
                    }
                });

                mQueue.add(request);
            }
        });
    }


}


