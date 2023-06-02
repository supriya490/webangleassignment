package com.example.webangleassignment.Network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIs {
    String BASE_URL="https://www.thehrindia.com/api/";
    @GET("getjobs")
    Call<JobList> getJobs();

    @GET("getjobs/{id}/")
    Call<JobList> getJob(@Path("id") int jobid);
}
