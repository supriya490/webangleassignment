package com.example.webangleassignment;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.widget.SearchView;

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
    RvJobsAdapter rvJobsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_users);
        rvJobs = findViewById(R.id.rv_users);

        getJobs();
    }

    private void getJobs() {
        Call<JobList> apiCall = RetrofitClient.getInstance().getApis().getJobs();
        apiCall.enqueue(new Callback<JobList>() {
            @Override
            public void onResponse(Call<JobList> call, Response<JobList> response) {
                JobList jobList = response.body();
                List<JobResult> jobResults = jobList.getJobResultList();
                Toast.makeText(ActivityRvJobs.this, "Got the list of Jobs", Toast.LENGTH_SHORT).show();
                setAdapater(jobResults);
            }

            @Override
            public void onFailure(Call<JobList> call, Throwable t) {
                Toast.makeText(ActivityRvJobs.this, "Error :" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("api", "Error: " + t.getMessage());
            }
        });

    }

    private void setAdapater(List<JobResult> jobResults) {
        rvJobs.setLayoutManager(new LinearLayoutManager(this));
        rvJobsAdapter = new RvJobsAdapter(this, jobResults);
        rvJobs.setAdapter(rvJobsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.usermodelmenu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                rvJobsAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
