package com.example.blooddonetion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread t1=new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(2000);
                    Intent i = new Intent(MainActivity.this,viewpageactivty.class);
                    startActivity(i);
                    finish();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }};

        t1.start();
    }
}
