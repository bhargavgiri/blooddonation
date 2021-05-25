package com.example.blooddonetion;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetNewLocation extends AppCompatActivity{

    private static final int REQUEST_LOCATION = 1;
    private LocationManager locationManager;
    Double lat;
    Double longt;
  public static String fullAddress;

    Geocoder geocoder;
    List<android.location.Address> addresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_new_location);

        ActivityCompat.requestPermissions(GetNewLocation.this,new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);

        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            OnGPS();
        }else{
            OnGPS();
            getLocation();

            geocoder=new Geocoder(GetNewLocation.this, Locale.getDefault());
            try {
                addresses=geocoder.getFromLocation(lat, longt , 1);

                String add=addresses.get(0).getAddressLine(0);
                String area = addresses.get(0).getLocality();
                String city = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();

                fullAddress=add+","+area+","+city+","+country+","+postalCode;

                startActivity(new Intent(GetNewLocation.this,CreateAccount.class));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getLocation() {
        if(ActivityCompat.checkSelfPermission(GetNewLocation.this,
                Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(GetNewLocation.this,
                Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                   ActivityCompat.requestPermissions(this,new String[]
                           {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);
        }else{
            Location LocationGPS =locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location LocationNetwork=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location LocationPassive=locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if(LocationGPS!=null){
                lat=LocationGPS.getLatitude();
                longt=LocationGPS.getLongitude();


            }else if(LocationNetwork!=null){
                lat=LocationNetwork.getLatitude();
                longt=LocationNetwork.getLongitude();


            }else if(LocationPassive!=null){
                lat=LocationPassive.getLatitude();
                longt=LocationPassive.getLongitude();


            }else{
                Toast.makeText(this, "Can't Get Your Location", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void OnGPS() {
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("enable GPS").setCancelable(false).setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
