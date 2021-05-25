package com.example.blooddonetion;

public class PationData {
    String PatientName ;
    String Phone_no;
    String City;
    String Hospital_Addrss;
    String Sp;

    public PationData(String patientName, String phone_no, String city, String hospital_Addrss, String sp) {
        PatientName = patientName;
        Phone_no = phone_no;
        City = city;
        Hospital_Addrss = hospital_Addrss;
        Sp = sp;
    }

    public PationData() {
    }


    public String getPatientName() {
        return PatientName;
    }
    public void setPatientName(String patientName) {
        PatientName = patientName;
    }
    public String getPhone_no() {
        return Phone_no;
    }
    public void setPhone_no(String phone_no) {
        Phone_no = phone_no;
    }
    public String getCity() {
        return City;
    }
    public void setCity(String city) {
        City = city;
    }
    public String getHospital_Addrss() {
        return Hospital_Addrss;
    }
    public void setHospital_Addrss(String hospital_Addrss) {
        Hospital_Addrss = hospital_Addrss;
    }
    public String getSp() {
        return Sp;
    }
    public void setSp(String sp) {
        Sp = sp;
    }
}
