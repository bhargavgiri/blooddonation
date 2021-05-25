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

public class viewpageactivty extends AppCompatActivity {
    Button l,c;
    VideoView mVideoView;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpageactivty);
        l=(Button)findViewById(R.id.lo1);
        c=(Button)findViewById(R.id.c1);
        auth=FirebaseAuth.getInstance();

        VideoView videoView = (VideoView) findViewById(R.id.login_video);
        Uri uri= Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video_line);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(viewpageactivty.this,loginactivity.class);
                startActivity(i);
                finish();
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(viewpageactivty.this,MaleFemalactivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    protected void onStart() {
        FirebaseUser user=auth.getCurrentUser();
        if(user!=null) {
            Intent i=new Intent(viewpageactivty.this,DiplayDonarelist.class);
            startActivity(i);
            finish();
        }
        super.onStart();
    }
}
