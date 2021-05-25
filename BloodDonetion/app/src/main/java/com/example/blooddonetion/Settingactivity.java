package com.example.blooddonetion;

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

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Settingactivity extends AppCompatActivity {
    CircleImageView c2;
    Button s;
    EditText n,f,p,phone4,w1;
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
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String na1=n.getText().toString();
                String fa1=f.getText().toString();
                String pin1=p.getText().toString();
                String pho1=phone4.getText().toString();
                String wi=w1.getText().toString();
                if (!TextUtils.isEmpty(na1) && !TextUtils.isEmpty(fa1) && !TextUtils.isEmpty(pin1) &&!TextUtils.isEmpty(pho1) && !TextUtils.isEmpty(wi))
                {
                    Intent i=new Intent(Settingactivity.this,DiplayDonarelist.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(Settingactivity.this,"fill the data",Toast.LENGTH_SHORT).show();
                }
            }
        });

        c2=findViewById(R.id.user);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {
                    if(ContextCompat.checkSelfPermission(Settingactivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(Settingactivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},101);
                    }
                    else
                    {
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
                Uri resultUri = result.getUri();
                c2.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

}
