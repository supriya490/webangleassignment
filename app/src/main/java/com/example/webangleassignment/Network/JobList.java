package com.example.webangleassignment.Network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobList {


    @SerializedName("data")
    private List<JobResult> jobResultList;
    public List<JobResult> getJobResultList() {
        return jobResultList;
    }
}
