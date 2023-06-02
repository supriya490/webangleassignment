package com.example.webangleassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webangleassignment.Network.JobList;
import com.example.webangleassignment.Network.JobResult;
import com.example.webangleassignment.Network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityJob extends AppCompatActivity {
    public int jobid;
    public JobResult jr;
    public TextView role,company,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        jobid= Integer.parseInt(getIntent().getStringExtra("jobIdB"));
        Toast.makeText(this, ""+jobid, Toast.LENGTH_SHORT).show();
        role=findViewById(R.id.role);
        company=findViewById(R.id.company);
        desc=findViewById(R.id.desc);
        getJob();
    }

    private void getJob() {
        Call<JobList> apiCall= RetrofitClient.getInstance().getApis().getJob(jobid);
        apiCall.enqueue(new Callback<JobList>() {
            @Override
            public void onResponse(Call<JobList> call, Response<JobList> response) {
                Log.v("apiresponse",""+response.body().getJobResultList());
                jr=response.body().getJobResultList().get(0);
                role.setText(jr.getRole());
                desc.setText(jr.getDiscription());
                company.setText("Company - "+ jr.getCompany());
            }

            @Override
            public void onFailure(Call<JobList> call, Throwable t) {
                Toast.makeText(ActivityJob.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("api","Error: "+t.getMessage());
            }
        });
    }

}