package com.example.blooddonetion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Settingactivity extends AppCompatActivity {
    CircleImageView c2;
    Button s;
    FirebaseAuth firebaseAuth;
    EditText n,f,p,phone4,w1;
    Uri resultUri;
    StorageReference storageReference;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingactivity);
        n=findViewById(R.id.name2);
        f=findViewById(R.id.lastn);
        p=findViewById(R.id.pincod2);
        phone4=findViewById(R.id.call2);
        w1=findViewById(R.id.weight6);
        s=findViewById(R.id.save);
        firebaseAuth=FirebaseAuth.getInstance();
        storageReference=FirebaseStorage.getInstance().getReference();
        firebaseFirestore=FirebaseFirestore.getInstance();

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 final String na1=n.getText().toString();
                final String fa1=f.getText().toString();
                final String pin1=p.getText().toString();
                final String pho1=phone4.getText().toString();
                final String wi=w1.getText().toString();

                final String current=firebaseAuth.getCurrentUser().getUid();

                if (!TextUtils.isEmpty(na1) && !TextUtils.isEmpty(fa1) && !TextUtils.isEmpty(pin1) &&!TextUtils.isEmpty(pho1) && !TextUtils.isEmpty(wi)) {

                    storageReference.child("profile").child(current+".jpg").putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            if(MaleFemalactivity.gender=="BloodDonner")
                            {
                                Updatedata updatedata=new Updatedata(na1,fa1,pin1,pho1,wi);
                                firebaseFirestore.collection(" BloodDonner").document(current).set(updatedata).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(Settingactivity.this, "profile is updedeted", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(Settingactivity.this, "fail", Toast.LENGTH_SHORT).show();

                                    }
                                });

                            }
                            if(MaleFemalactivity.gender=="BloodDonner")

                            {

                                Updatedata updatedata1=new Updatedata(na1,fa1,pin1,pho1,wi);
                                firebaseFirestore.collection("BloodRequest").document(current).set(updatedata1).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                        Toast.makeText(Settingactivity.this, "Update data Successfully", Toast.LENGTH_SHORT).show();

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        Toast.makeText(Settingactivity.this, "Fail", Toast.LENGTH_SHORT).show();

                                    }
                                });

                            }


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Settingactivity.this, "Error While Uploading", Toast.LENGTH_SHORT).show();
                        }
                    });


                } else {
                    Toast.makeText(Settingactivity.this,"fill the data",Toast.LENGTH_SHORT).show();
                }
            }
        });

        c2=findViewById(R.id.user);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    if(ContextCompat.checkSelfPermission(Settingactivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Settingactivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},101);
                    } else {
                        CropImage.activity()
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .start(Settingactivity.this);
                    }
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                 resultUri = result.getUri();
                c2.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

}
