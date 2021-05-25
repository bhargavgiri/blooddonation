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
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Diplayactivity extends AppCompatActivity {
    CircleImageView c1;
    EditText n1,l,p,w;
    Spinner s,age;
    String name[]={"Select blood","A+","A-","B+","B-","AB+","AB-","O+","O-","A1+","A1-","A2+","A2-","A1B+","A1B-","A2B+","A2B-","Bombay blood Group"};
    String a[]={"18","19","20","21","22","23","24","25","26","27","29","30","31","32","33","34","35","36","37","38","39","40","Above 40"};
    Button n;
    Uri resultUri;
    static String profilepath;
    String bgroup;
    String bage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diplayactivity);
        n=findViewById(R.id.next);
        s=findViewById(R.id.bgrouppp);

        n1=findViewById(R.id.fname);
        l=findViewById(R.id.last);
        p=findViewById(R.id.pincod);
        w=findViewById(R.id.weight);
        age=findViewById(R.id.age);

        storageReference= FirebaseStorage.getInstance().getReference();

        c1=findViewById(R.id.profile_image);
        ArrayAdapter<String> ad=new ArrayAdapter<String>(Diplayactivity.this,R.layout.support_simple_spinner_dropdown_item,name);
        ad.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        s.setAdapter(ad);

        s.setSelection(0);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==1) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                    Toast.makeText(Diplayactivity.this, bgroup, Toast.LENGTH_SHORT).show();
                } else if(i==2) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==3) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==4) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==5) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==6) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==7) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==8) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==9) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==10) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==11){
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==12) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==13) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==14) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==15) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==16) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                } else if(i==17) {
                    int item=s.getSelectedItemPosition();
                    bgroup=name[item];
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        ArrayAdapter<String> ad2=new ArrayAdapter<String>(Diplayactivity.this,R.layout.support_simple_spinner_dropdown_item,a);
        ad2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        age.setAdapter(ad2);
        age.setSelection(0);
        age.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(i==0)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==1)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==2)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==3)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==4)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==5)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==6)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==7)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==8) {
                    int item = age.getSelectedItemPosition();
                    bage = name[item];
                }
                if(i==9)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==10)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==11)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==12)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==13)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==14)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==15)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==16)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==17)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==18)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==19)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==20)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==21)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }
                if(i==22)
                {
                    int item=age.getSelectedItemPosition();
                    bage=name[item];
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
                    if(ContextCompat.checkSelfPermission(Diplayactivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Diplayactivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},101);
                    } else {
                        CropImage.activity()
                                .setGuidelines(CropImageView.Guidelines.ON)
                                .start(Diplayactivity.this);
                    }
                }
            }
        });
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fname=n1.getText().toString().trim() ;
                String last=l.getText().toString().trim();
                String  pin=p.getText().toString().trim();
                String  wi=w.getText().toString().trim();


                if (!TextUtils.isEmpty(fname) && !TextUtils.isEmpty(last) && !TextUtils.isEmpty(pin) && !TextUtils.isEmpty(wi) && resultUri!=null) {
                    storageReference.child("profile").child(fname+".jpg").putFile(resultUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(Diplayactivity.this, "Profile Uploaded", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Diplayactivity.this, "Error While Uploading", Toast.LENGTH_SHORT).show();
                        }
                    });


                    Intent i=new Intent(Diplayactivity.this,RequastForBlood.class);

                    i.putExtra("fname",fname);
                    i.putExtra("lname",last);
                    i.putExtra("pincode",pin);
                    i.putExtra("weight",wi);
                    i.putExtra("bloodgroup",bgroup);
                    i.putExtra("age",bage);

                    startActivity(i);
                } else {
                    Toast.makeText(Diplayactivity.this,"Plse fill the fields",Toast.LENGTH_SHORT).show();
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
                 profilepath=resultUri.toString();
                c1.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
}

