package com.example.blooddonetion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

public class notification extends AppCompatActivity {
    EditText notificationText;
    Button sendNotification;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    String user_id;
    private String donner_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationText=findViewById(R.id.NotificationText);
        sendNotification=findViewById(R.id.sendNotification);
        firestore=FirebaseFirestore.getInstance();
        donner_id=FirebaseAuth.getInstance().getUid();
        auth=FirebaseAuth.getInstance();

        sendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String message=notificationText.getText().toString();
                if(!TextUtils.isEmpty(message)){

                    Map<String,Object> notificationMessage=new HashMap<>();
                    notificationMessage.put("message",message);
                    notificationMessage.put("from",donner_id);
                    auth=FirebaseAuth.getInstance();
                    user_id=auth.getCurrentUser().getUid();
                    firestore.collection(" BloodDonner").document(user_id).collection("notification").add(notificationMessage).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(notification.this, "Notification Sent...", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(notification.this, "Error.."+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
    }
}
