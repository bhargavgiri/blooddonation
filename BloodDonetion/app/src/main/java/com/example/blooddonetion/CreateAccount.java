package com.example.blooddonetion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scrounger.countrycurrencypicker.library.Buttons.CountryCurrencyButton;
import com.scrounger.countrycurrencypicker.library.Country;
import com.scrounger.countrycurrencypicker.library.Currency;
import com.scrounger.countrycurrencypicker.library.Listener.CountryCurrencyPickerListener;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateAccount extends AppCompatActivity {
    FirebaseAuth auth;
    EditText e,p,c;
    Button submit1;

  //public  static   String sssfname;
  //  String ssslname,ssspincode,ssswight,sssmobile;

  //  DatabaseReference male;
   // DatabaseReference female;

    Blooddata blooddata;

   /* long maxid=0;
    long maxid1=0;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        auth=FirebaseAuth.getInstance();
        e=findViewById(R.id.email);
        p=findViewById(R.id.pass);
        c=findViewById(R.id.conpa);
        submit1=findViewById(R.id.submit);

        blooddata=new Blooddata();
       /* male=FirebaseDatabase.getInstance().getReference().child("Male");
        female=FirebaseDatabase.getInstance().getReference().child("Female");

        male.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        female.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                    maxid1=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/



        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email1=e.getText().toString();
                final String pass1=p.getText().toString();
                final String conpa1=c.getText().toString();

               /* sssfname=getIntent().getStringExtra("ssfname");
                ssslname=getIntent().getStringExtra("sslname");
                ssspincode=getIntent().getStringExtra("sspincode");
                ssswight=getIntent().getStringExtra("ssweight");
                sssmobile=getIntent().getStringExtra("ssmobile");*/


                if(!TextUtils.isEmpty(email1) && !TextUtils.isEmpty(pass1) && !TextUtils.isEmpty(conpa1))
                {
                    if(pass1.equals(conpa1))
                    {
                        auth.createUserWithEmailAndPassword(email1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {


                                    auth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {


                                            if(task.isSuccessful())
                                            {
                                              /*  blooddata.setFname(sssfname);
                                                blooddata.setLname(ssslname);
                                                blooddata.setPincode(ssspincode);
                                                blooddata.setWeight(ssswight);
                                                blooddata.setMobile(sssmobile);
                                                blooddata.setEmail(email1);*/

                                                if(MaleFemalactivity.gender=="female")
                                                {

                                                    //blooddata.setFemale(MaleFemalactivity.gender);
                                                   // female.child(String.valueOf(maxid1+1)).setValue(blooddata);


                                                }
                                               if(MaleFemalactivity.gender=="male")
                                                {
                                                 //   blooddata.setMale(MaleFemalactivity.gender);
                                                 //   male.child(String.valueOf(maxid+1)).setValue(blooddata);

                                                }



                                                Toast.makeText(CreateAccount.this,"Please Check Your Gmail",Toast.LENGTH_SHORT).show();


                                                auth.signOut();

                                                Intent i=new Intent(CreateAccount.this,loginactivity.class);



                                                startActivity(i);
                                                finish();

                                            }

                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(CreateAccount.this,"Somthing wrong",Toast.LENGTH_LONG).show();
                                }

                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(CreateAccount.this,"Password is not match",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(CreateAccount.this,"fill The ditails",Toast.LENGTH_LONG).show();
                }
            }
        });
   }

}
