package com.example.hospitalmanagement;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    //    public static final String BaseUrl = "http://192.168.2.20:80/";
public static final String BaseUrl = "http://10.0.2.2:3000/";
//    public static final String BaseUrl="http://127.0.0.1:3000/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit=new Retrofit.Builder ( )
                    .baseUrl ( BaseUrl )
                    .addConverterFactory ( GsonConverterFactory.create ( ) )
                    .build ( );
        }
        return retrofit;
    }
}
