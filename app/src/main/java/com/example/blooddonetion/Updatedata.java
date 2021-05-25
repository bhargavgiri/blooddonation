package com.example.blooddonetion;

public class Updatedata {

    String fname,lname,pincode,weight,mobile;

    public Updatedata(String fname, String lname, String pincode, String weight, String mobile) {
        this.fname = fname;
        this.lname = lname;
        this.pincode = pincode;
        this.weight = weight;
        this.mobile = mobile;
    }

    public Updatedata() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
