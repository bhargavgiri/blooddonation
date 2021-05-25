package com.example.blooddonetion;

public class Blooddata {

    private static String Sp;
    private String Fname;
    private String Lname;
    private String Pincode;
    private String weight;
    private String Male;
    private String Female;
    private String Mobile;
    private String Email;




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

    public String getMale() {
        return Male;
    }

    public void setMale(String male) {
        Male = male;
    }

    public String getFemale() {
        return Female;
    }

    public void setFemale(String female) {
        Female = female;
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

    public String getSp() {
        return Sp;
    }

    public static void setSp(String sp) {
        Sp = sp;
    }
}
