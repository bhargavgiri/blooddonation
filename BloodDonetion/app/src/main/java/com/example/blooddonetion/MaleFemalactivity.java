package com.example.blooddonetion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MaleFemalactivity extends AppCompatActivity {
ImageView m,f;
    static String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_femalactivity);
        m=findViewById(R.id.male);
        f=findViewById(R.id.female);

        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



              gender="male";
                Intent i=new Intent(MaleFemalactivity.this,Diplayactivity.class);
                startActivity(i);



            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gender="female";

                Intent i=new Intent(MaleFemalactivity.this,Diplayactivity.class);
                startActivity(i);



            }
        });
    }

}
