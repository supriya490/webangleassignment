package com.example.webangleassignment.Network;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobResult {


    @SerializedName("id")
    int id;

    @SerializedName("role")
    String role;
    @SerializedName("company")
    String company;
    @SerializedName("discription")
    String discription;


    public String getCompany() {
        return company;
    }

    public String getDiscription() {
        return discription;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
