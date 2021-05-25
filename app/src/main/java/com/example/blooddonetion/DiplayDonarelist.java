package com.example.blooddonetion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DiplayDonarelist extends AppCompatActivity {

    Toolbar tool;
    FirebaseAuth auth;
    long time=0;
    RecyclerView recyclerView;
    List<Blooddata> list;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diplay_donarelist);
        auth=FirebaseAuth.getInstance();
        tool=findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recylerView);
        list=new ArrayList<>();
        firestore=FirebaseFirestore.getInstance();


        firestore.collection(" BloodDonner").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if(!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> dsList=queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : dsList) {
                        Blooddata e=d.toObject(Blooddata.class);
                        list.add(e);
                    }

                    RecyclerAdapter recyclerAdapter = new RecyclerAdapter(DiplayDonarelist.this,list);
                    recyclerView.setAdapter(recyclerAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(DiplayDonarelist.this));

                }
                else {
                    Toast.makeText(DiplayDonarelist.this, "Data is Empty", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DiplayDonarelist.this, "Failed To Load", Toast.LENGTH_SHORT).show();
            }
        });
        setSupportActionBar(tool);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.itemfile,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.set1) {
            Intent i1=new Intent(DiplayDonarelist.this,ProfileActivity.class);
            startActivity(i1);
        }
        if(item.getItemId()==R.id.log1) {
            auth.signOut();
            Intent i=new Intent(DiplayDonarelist.this,loginactivity.class);
            startActivity(i);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        FirebaseUser user=auth.getCurrentUser();
        if(user==null) {
            Intent i=new Intent(DiplayDonarelist.this,loginactivity.class);
            startActivity(i);
            finish();
        }
        super.onStart();


    }


    @Override
    public void onBackPressed() {

        if (time+2000>System.currentTimeMillis()) {
            DiplayDonarelist. super.onBackPressed();
        } else
        {
            Toast.makeText(DiplayDonarelist.this,"Wants to exit",Toast.LENGTH_SHORT).show();
        }time=System.currentTimeMillis();
    }
}
