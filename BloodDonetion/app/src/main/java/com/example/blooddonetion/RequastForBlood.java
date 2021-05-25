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

       s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              /* if(i==0)
               {
                   pationData.setSp(name[0]);
               }
               else if(i==1)
               {
                   pationData.setSp(name[1]);
               }
               else if(i==2)
               {
                   pationData.setSp(name[2]);
               }
               else if(i==3)
               {
                   pationData.setSp(name[3]);
               }
               else if(i==4)
               {
                   pationData.setSp(name[4]);
               }
               else if(i==5)
               {
                   pationData.setSp(name[5]);
               }
               else if(i==6)
               {
                   pationData.setSp(name[6]);
               }
               else if(i==7)
               {
                   pationData.setSp(name[7]);
               }
               else if(i==8)
               {
                   pationData.setSp(name[8]);
               }
               else if(i==9)
               {
                   pationData.setSp(name[9]);
               }
               else if(i==10)
               {
                   pationData.setSp(name[10]);
               }
               else if(i==11)
               {
                   pationData.setSp(name[11]);
               }
               else if(i==12)
               {
                   pationData.setSp(name[12]);
               }
               else if(i==13)
               {
                   pationData.setSp(name[13]);
               }
               else if(i==14)
               {
                   pationData.setSp(name[14]);
               }
               else if(i==15)
               {
                   pationData.setSp(name[15]);
               }
               else if(i==16)
               {
                   pationData.setSp(name[16]);
               }
               else if(i==17)
               {
                   pationData.setSp(name[17]);
               }*/

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
               String p7=e4.getText().toString().trim();
                // group=name.toString().trim();syrdhfsgfksjfz
               if(!TextUtils.isEmpty(p4) && !TextUtils.isEmpty(p5) && !TextUtils.isEmpty(p6) && !TextUtils.isEmpty(p7))
               {
                   Intent i=new Intent(RequastForBlood.this,DiplayDonarelist.class);
                   pationData.setPatientName(p4);
                   pationData.setPhone_no(p5);
                   pationData.setCity(p6);
                   pationData.setHospital_Addrss(p7);
                   data.child(String.valueOf(maxid+1)).setValue(pationData);

                   startActivity(i);

               }
               else
               {
                   Toast.makeText(RequastForBlood.this," fill the details",Toast.LENGTH_SHORT).show();
               }
           }
       });

    }
}
