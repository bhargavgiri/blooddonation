package com.example.blooddonetion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RequastForBlood extends AppCompatActivity {
    Spinner s;
    String name[]={"Select blood","A+","A-","B+","B-","AB+","AB-","O+","O-","A1+","A1-","A2+","A2-","A1B+","A1B-","A2B+","A2B-","Bombay blood Group"};
    Button n;
    String group;

    static String bname,blastname,bpincode,bweight,bbloodgroup,age,pbloogdroup,hhaddress;

    EditText e1,e2,e3,e4;
    PationData pationData;

    DatabaseReference data;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requast_for_blood);

        e1=findViewById(R.id.pation);
        e2=findViewById(R.id.call3);
        e3=findViewById(R.id.city3);
        e4=findViewById(R.id.h_address);

        bname=getIntent().getStringExtra("fname");
        blastname=getIntent().getStringExtra("lname");
        bpincode=getIntent().getStringExtra("pincode");
        bweight=getIntent().getStringExtra("weight");
        bbloodgroup=getIntent().getStringExtra("bloodgroup");
        age=getIntent().getStringExtra("age");

        pationData= new PationData();
        data= FirebaseDatabase.getInstance().getReference().child("patient Data");

        data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        s=findViewById(R.id.bgroup);
        n=findViewById(R.id.next1);
       ArrayAdapter<String> ad=new ArrayAdapter<String>(RequastForBlood.this,R.layout.support_simple_spinner_dropdown_item,name);
       ad.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
       s.setAdapter(ad);
       s.setSelection(0);

       s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if(i==0) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==1) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==2) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==3) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==4) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==5) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==6) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==7) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==8) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==9) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==10) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==11) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==12) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==13) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==14) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==15) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==16) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               } else if(i==17) {
                   int item=s.getSelectedItemPosition();
                   pbloogdroup=name[item];
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {
           }
       });

       n.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String p4=e1.getText().toString().trim();
               String p5=e2.getText().toString().trim();
               String p6=e3.getText().toString().trim();
               hhaddress=e4.getText().toString().trim();
                // group=name.toString().trim();
               if(!TextUtils.isEmpty(p4) && !TextUtils.isEmpty(p5) && !TextUtils.isEmpty(p6) && !TextUtils.isEmpty(hhaddress)) {
                   Intent i=new Intent(RequastForBlood.this,GetNewLocation.class);
                   startActivity(i);
               } else {
                   Toast.makeText(RequastForBlood.this," fill the details",Toast.LENGTH_SHORT).show();
               }
           }
       });

    }
}
