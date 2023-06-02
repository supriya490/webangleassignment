package com.example.webangleassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
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
    public TextView role,company,desc,date,location,area,etype,vacancies,exp,degree;
    Button salary,callbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);
        jobid= Integer.parseInt(getIntent().getStringExtra("jobIdB"));
        Toast.makeText(this, ""+jobid, Toast.LENGTH_SHORT).show();
        role=findViewById(R.id.role);
        company=findViewById(R.id.company);
        desc=findViewById(R.id.desc);
        location=findViewById(R.id.location);
        date=findViewById(R.id.date);
        area=findViewById(R.id.area);
        etype=findViewById(R.id.etype);
        vacancies=findViewById(R.id.vacancies);
        exp=findViewById(R.id.exp);
        degree=findViewById(R.id.degree);
        salary=findViewById(R.id.salary);
        callbtn=findViewById(R.id.callbtn);
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
                date.setText("Posted on - "+jr.getDate());
                location.setText("Location - " +jr.getLocation());
                area.setText("Industry - " +jr.getCategory());
                etype.setText("Employment type - " +jr.getType());
                vacancies.setText("Vacancies - " +jr.getVacancy());
                exp.setText("Experiences - " +jr.getExperience());
                degree.setText("Experiences - " +jr.getQualification());
                salary.setText("Salary: "+jr.getSalary());
                callbtn.setText("Call HR:"+jr.getCall_hr());


            }

            @Override
            public void onFailure(Call<JobList> call, Throwable t) {
                Toast.makeText(ActivityJob.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("api","Error: "+t.getMessage());
            }
        });
    }

}