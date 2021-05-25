package com.example.blooddonetion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class ProfileActivity extends AppCompatActivity {

    Button edit;
    FirebaseAuth auth;
    String userid;

    FirebaseFirestore fstore1;

    TextView fname3,lname3,weight3,pincode3,mobile3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        auth= FirebaseAuth.getInstance();
        userid=auth.getCurrentUser().getUid();
        fstore1= FirebaseFirestore.getInstance();
        fname3=(TextView)findViewById(R.id.fname1);
        lname3=(TextView)findViewById(R.id.lname);
        weight3=(TextView) findViewById(R.id.weight1);
        pincode3=(TextView) findViewById(R.id.pincode);
        mobile3=(TextView) findViewById(R.id.number2);


        fstore1.collection("AllUser").document(userid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {


                    String f1=documentSnapshot.getString("fname");
                    String l1=documentSnapshot.getString("lname");
                    String pi=documentSnapshot.getString("pincode");
                    String m1=documentSnapshot.getString("mobile");
                    String w1=documentSnapshot.getString("weight");
                Toast.makeText(ProfileActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    fname3.setText(f1);
                    lname3.setText(l1);
                    pincode3.setText(pi);
                    mobile3.setText(m1);
                    weight3.setText(w1);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(ProfileActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
        edit=findViewById(R.id.setting);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ProfileActivity.this,Settingactivity.class);
                startActivity(i);
            }
        });
    }
}
