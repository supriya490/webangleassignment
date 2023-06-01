package com.example.webangleassignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiinterface {

        @GET("/photos")
        Call<List<usermodel>>getUsers();
}
