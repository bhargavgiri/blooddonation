package com.example.blooddonetion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class loginactivity extends AppCompatActivity {
FirebaseAuth auth;
EditText e,p;
ImageButton b;
TextView t,t2;
FirebaseFirestore firebaseFirestore;
    private String donner_id;

    long time=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        auth=FirebaseAuth.getInstance();
        e=findViewById(R.id.em);
        p=findViewById(R.id.pa);
        t=findViewById(R.id.re);
        t2=findViewById(R.id.forget);
        donner_id=FirebaseAuth.getInstance().getUid();
        firebaseFirestore=FirebaseFirestore.getInstance();
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i=new Intent(loginactivity.this,ForgetPassword.class);
              startActivity(i);
            }
        });


        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(loginactivity.this,MaleFemalactivity.class);
                startActivity(i);
            }
        });

        b=findViewById(R.id.log1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=e.getText().toString();
                String pass=p.getText().toString();
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
                    auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                               if(auth.getCurrentUser().isEmailVerified()) {

                                    Intent i=new Intent(loginactivity.this,DiplayDonarelist.class);
                                    startActivity(i);
                                    finish();

                               } else {
                                   Toast.makeText(loginactivity.this,"Please Check Your Gmail",Toast.LENGTH_SHORT).show();
                               }
                            } else {
                                Toast.makeText(loginactivity.this,"Cheak email or password",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(loginactivity.this,"Data not found",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (time+2000>System.currentTimeMillis()) {
            loginactivity. super.onBackPressed();
        } else {
            Toast.makeText(loginactivity.this,"Wants to exit",Toast.LENGTH_SHORT).show();
        }time=System.currentTimeMillis();
    }

}
