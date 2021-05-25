package com.example.blooddonetion;

public class AllUser {
    private String Fname;
    private String Lname;
    private String Pincode;
    private String Wt;
    private String Mobile;
    public AllUser(String fname, String lname, String pincode, String wt, String mobile) {

        Fname = fname;
        Lname = lname;
        Pincode = pincode;
        Wt = wt;
        Mobile = mobile;
    }

    public AllUser() {
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

    public String getWt() {
        return Wt;
    }

    public void setWt(String wt) {
        Wt = wt;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }
}
