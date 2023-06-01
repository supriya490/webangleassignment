package com.example.webangleassignment;

import static com.example.webangleassignment.MainActivity.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retroinstance {
    public static retroinstance instance;
    apiinterface ainterface;
    retroinstance(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
      ainterface=  retrofit.create(apiinterface.class);
    }
    public static retroinstance getInstance(){
        if(instance==null){
            instance=new retroinstance();

        }
       return  instance;
    }

}
