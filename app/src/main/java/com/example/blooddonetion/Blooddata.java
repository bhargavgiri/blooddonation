package com.example.blooddonetion;

import android.widget.EditText;

public class Blooddata extends Userid {
    private String Fname;
    private String Lname;
    private String Pincode;
    private String weight;
    private  String Bloodgroup;
    private String profilepath;
    private String Mobile;
    private String Email;
    private String Patientboodgroup;
    private String Age;
    private String Address;
    private String fullAddress;

    public Blooddata(String fname, String lname, String pincode, String weight, String bloodgroup, String profilepath, String mobile, String email, String patientboodgroup, String age, String address, String fullAddress) {
        this.Fname = fname;
        this.Lname = lname;
        this.Pincode = pincode;
        this.weight = weight;
        this.Bloodgroup = bloodgroup;
        this.profilepath = profilepath;
        this.Mobile = mobile;
        this.Email = email;
        this.Patientboodgroup = patientboodgroup;
        this.Age = age;
        this.Address = address;
        this.fullAddress = fullAddress;
    }

    public Blooddata() {
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBloodgroup() {
        return Bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        Bloodgroup = bloodgroup;
    }

    public String getProfilepath() {
        return profilepath;
    }

    public void setProfilepath(String profilepath) {
        this.profilepath = profilepath;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPatientboodgroup() {
        return Patientboodgroup;
    }

    public void setPatientboodgroup(String patientboodgroup) {
        Patientboodgroup = patientboodgroup;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }
}
