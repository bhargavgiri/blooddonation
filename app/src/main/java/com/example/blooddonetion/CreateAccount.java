package com.example.blooddonetion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class CreateAccount extends AppCompatActivity {
    FirebaseAuth auth;
    EditText e, p, c, m;
    Button btn;

    String  bname=RequastForBlood.bname,blastname=RequastForBlood.blastname,
            bpincode=RequastForBlood.bpincode,bweight=RequastForBlood.bweight,
            bbloodgroup=RequastForBlood.bbloodgroup,age=RequastForBlood.age,
            pbloogdroup=RequastForBlood.pbloogdroup,hhaddress=RequastForBlood.hhaddress;

    String user_id;
    FirebaseFirestore fstore;
    Blooddata blooddata;
   String address;



    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
       address= GetNewLocation.fullAddress;

        Toast.makeText(this, address, Toast.LENGTH_SHORT).show();

        auth = FirebaseAuth.getInstance();
        e = findViewById(R.id.email);
        p = findViewById(R.id.pass);
        c = findViewById(R.id.conpa);
        btn = findViewById(R.id.btn);
        m = findViewById(R.id.reg_phone);


        fstore = FirebaseFirestore.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email1=e.getText().toString();
                final String pass1=p.getText().toString();
                final String conpa1=c.getText().toString();
                final String mobile=m.getText().toString();
                final String path=Diplayactivity.profilepath;

                if(!TextUtils.isEmpty(email1) && !TextUtils.isEmpty(pass1) && !TextUtils.isEmpty(conpa1) && !TextUtils.isEmpty(mobile)) {
                    if(pass1.equals(conpa1)) {
                        auth.createUserWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {
                                                if(MaleFemalactivity.gender=="BloodDonner") {
                                                    auth=FirebaseAuth.getInstance();
                                                    user_id=auth.getCurrentUser().getUid();
                                                    blooddata=new Blooddata(bname,blastname,bpincode,bweight,bbloodgroup,path,mobile,email1,pbloogdroup,age,hhaddress,address);
                                                    fstore.collection(" BloodDonner").document(user_id).set(blooddata).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {


                                                            AllUser allUser=new AllUser(bname,blastname,bpincode,bweight,mobile);
                                                            fstore.collection("AllUser").document(user_id).set(allUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {


                                                                    Intent i1=new Intent(CreateAccount.this,loginactivity.class);
                                                                    startActivity(i1);
                                                                    finish();
                                                                    auth.signOut();

                                                                    Toast.makeText(CreateAccount.this, "Succeess", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Toast.makeText(CreateAccount.this, "Fail", Toast.LENGTH_SHORT).show();
                                                                }
                                                            });






                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(CreateAccount.this, "Fail", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                                if(MaleFemalactivity.gender=="BloodRequest") {
                                                    auth=FirebaseAuth.getInstance();
                                                    user_id=auth.getCurrentUser().getUid();

                                                    blooddata=new Blooddata(bname,blastname,bpincode,bweight,bbloodgroup,path,mobile,email1,pbloogdroup,age,hhaddress,address);
                                                    fstore.collection("BloodRequest").document(user_id).set(blooddata).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {

                                                            AllUser allUser=new AllUser(bname,blastname,bpincode,bweight,mobile);
                                                            fstore.collection("AllUser").document(user_id).set(allUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {


                                                                    Intent i1=new Intent(CreateAccount.this,loginactivity.class);
                                                                    startActivity(i1);
                                                                    finish();
                                                                    auth.signOut();
                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    Toast.makeText(CreateAccount.this, "Fail", Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(CreateAccount.this, "Fail", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                                Toast.makeText(CreateAccount.this,"Please Check Your Gmail",Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                } else {
                                    Toast.makeText(CreateAccount.this,"Something wrong",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(CreateAccount.this,"Password is not match",Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CreateAccount.this,"fill The ditails",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    }

