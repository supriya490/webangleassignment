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

    @SerializedName("date")
    String date;

    @SerializedName("salary")
    String salary;

    @SerializedName("vacancy")
    String vacancy;

    @SerializedName("location")
    String location;



    @SerializedName("category")
    String category;

    @SerializedName("type")
    String type;





    @SerializedName("experience")
    String experience;

    @SerializedName("gender")
    String gender;

    @SerializedName("qualification")
    String qualification;

    @SerializedName("sector")
    String sector;

    @SerializedName("call_hr")
    String call_hr;


    public String getDate() {
        return date;
    }

    public String getSalary() {
        return salary;
    }

    public String getVacancy() {
        return vacancy;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getExperience() {
        return experience;
    }

    public String getGender() {
        return gender;
    }

    public String getQualification() {
        return qualification;
    }

    public String getSector() {
        return sector;
    }

    public String getCall_hr() {
        return call_hr;
    }

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
