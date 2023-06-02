package com.example.webangleassignment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webangleassignment.Network.JobList;
import com.example.webangleassignment.Network.JobResult;
import com.example.webangleassignment.Network.RetrofitClient;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityRvJobs extends AppCompatActivity {
        RecyclerView rvJobs;
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_rv_users);
                rvJobs=findViewById(R.id.rv_users);

                getJobs();
        }

        private void getJobs() {
                Call<JobList> apiCall = RetrofitClient.getInstance().getApis().getJobs();
                apiCall.enqueue(new Callback<JobList>() {
                        @Override
                        public void onResponse(Call<JobList> call, Response<JobList> response) {
                                JobList jobList=response.body();
                                List<JobResult> jobResults=jobList.getJobResultList();
                                Toast.makeText(ActivityRvJobs.this, "Got the list of Jobs", Toast.LENGTH_SHORT).show();
                                setAdapater(jobResults);
                        }

                        @Override
                        public void onFailure(Call<JobList> call, Throwable t) {
                                Toast.makeText(ActivityRvJobs.this, "Error :"+t.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("api","Error: "+t.getMessage());
                        }
                });

        }

        private void setAdapater(List<JobResult> jobResults) {
                rvJobs.setLayoutManager(new LinearLayoutManager(this));
                RvJobsAdapter rvJobsAdapter=new RvJobsAdapter(this,jobResults);
                rvJobs.setAdapter(rvJobsAdapter);
        }
}
