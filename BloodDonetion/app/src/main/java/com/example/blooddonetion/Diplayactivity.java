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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Diplayactivity extends AppCompatActivity {
    CircleImageView c1;
    EditText n1,l,p,w;
    Spinner s;
    String name[]={"Select blood","A+","A-","B+","B-","AB+","AB-","O+","O-","A1+","A1-","A2+","A2-","A1B+","A1B-","A2B+","A2B-","Bombay blood Group"};
    Button n;

    private StorageReference mImageStorage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diplayactivity);
        n=findViewById(R.id.next);
        s=findViewById(R.id.bgroup);

        n1=findViewById(R.id.fname);
        l=findViewById(R.id.last);
        p=findViewById(R.id.pincod);
        w=findViewById(R.id.weight);

        mImageStorage= FirebaseStorage.getInstance().getReference();




        c1=findViewById(R.id.profile_image);
        ArrayAdapter<String> ad=new ArrayAdapter<String>(Diplayactivity.this,R.layout.support_simple_spinner_dropdown_item,name);
        ad.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        s.setAdapter(ad);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {
                    if(ContextCompat.checkSelfPermission(Diplayactivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(Diplayactivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},101);
                    }
                    else
                    {
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


                s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 /*       if(i==0)
                        {
                            Blooddata.setSp(name[0]);
                        }
                        else if(i==1)
                        {
                            Blooddata.setSp(name[1]);
                        }
                        else if(i==2)
                        {
                            Blooddata.setSp(name[2]);
                        }
                        else if(i==3)
                        {
                            Blooddata.setSp(name[3]);
                        }
                        else if(i==4)
                        {
                            Blooddata.setSp(name[4]);
                        }
                        else if(i==5)
                        {
                            Blooddata.setSp(name[5]);
                        }
                        else if(i==6)
                        {
                            Blooddata.setSp(name[6]);
                        }
                        else if(i==7)
                        {
                            Blooddata.setSp(name[7]);
                        }
                        else if(i==8)
                        {
                            Blooddata.setSp(name[8]);
                        }
                        else if(i==9)
                        {
                            Blooddata.setSp(name[9]);
                        }
                        else if(i==10)
                        {
                            Blooddata.setSp(name[10]);
                        }
                        else if(i==11)
                        {
                            Blooddata.setSp(name[11]);
                        }
                        else if(i==12)
                        {
                            Blooddata.setSp(name[12]);
                        }
                        else if(i==13)
                        {
                            Blooddata.setSp(name[13]);
                        }
                        else if(i==14)
                        {
                            Blooddata.setSp(name[14]);
                        }
                        else if(i==15)
                        {
                            Blooddata.setSp(name[15]);
                        }
                        else if(i==16)
                        {
                            Blooddata.setSp(name[16]);
                        }
                        else if(i==17)
                        {
                           Blooddata.setSp(name[17]);
                        }*/

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                if (!TextUtils.isEmpty(fname) && !TextUtils.isEmpty(last) && !TextUtils.isEmpty(pin) && !TextUtils.isEmpty(wi))
                {
                    Intent i=new Intent(Diplayactivity.this,OtpActivity.class);

                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(Diplayactivity.this,"Plse fill the dityail",Toast.LENGTH_SHORT).show();
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
                final Uri resultUri = result.getUri();

                StorageReference filepath=mImageStorage.child("profile").child(".jpg");

                filepath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            c1.setImageURI(resultUri);
                            Toast.makeText(Diplayactivity.this,"Success Uploading",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(Diplayactivity.this, "Error in Uploading.", Toast.LENGTH_SHORT).show();

                        }


                    }
                });

                c1.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }



}

