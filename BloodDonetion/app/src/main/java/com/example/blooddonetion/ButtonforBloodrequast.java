package com.example.blooddonetion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ButtonforBloodrequast extends AppCompatActivity {
    FirebaseAuth auth;
    Button r,d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttonfor_bloodrequast);
        r=findViewById(R.id.request);
        d=findViewById(R.id.donet);
        auth=FirebaseAuth.getInstance();





        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ButtonforBloodrequast.this,RequastForBlood.class);
                startActivity(i);

            }
        });
    }

    @Override
    protected void onStart() {
        FirebaseUser user=auth.getCurrentUser();
        if(user==null)
        {
            Intent i=new Intent(ButtonforBloodrequast.this,loginactivity.class);
            startActivity(i);
            finish();
        }
        super.onStart();
    }
}
