package com.example.hospitalmanagement;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Patient {

    @SerializedName("patientID")
    private int patientID;

    @SerializedName("name")
    private  String name;
    @SerializedName("phone")
    private  String phone;
    @SerializedName("age")
    private  int age;
    @SerializedName("dob")
    private Date dob;
    @SerializedName("email")
    private  String email;
    @SerializedName("bloodGroup")
    private  String bloodGroup;

    Patient(){

    }

    public Patient(String name, String phone, int age, String email, String bloodGroup) {
        this.name=name;
        this.phone=phone;
        this.age=age;
        this.email=email;
        this.bloodGroup=bloodGroup;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID=patientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone=phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age=age;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob=dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup=bloodGroup;
    }
}
