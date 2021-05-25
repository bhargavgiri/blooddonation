package com.example.blooddonetion;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    Context mContext;
    List<Blooddata> list;
    String userId;
    String getname;
    FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseFirestore firestore=FirebaseFirestore.getInstance();
    String uid=auth.getCurrentUser().getUid();
    String sposition;
    String no;


    public RecyclerAdapter(Context mContext, List<Blooddata> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {





        holder.fname.setText(list.get(position).getFname());
        holder.mNumber.setText(list.get(position).getMobile());
        holder.bGroup.setText(list.get(position).getBloodgroup());




        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PopupMenu popup=new PopupMenu(mContext,holder.layout);
                popup.getMenuInflater().inflate(R.menu.calltrackmenu,popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()==R.id.notification){

                            sendSMS(list.get(position).getMobile());

                        }
                        if(item.getItemId()==R.id.call){
                            Toast.makeText(mContext, list.get(position).getMobile(), LENGTH_SHORT).show();
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", list.get(position).getMobile(), null));
                            mContext.startActivity(intent);
                        }
                        if(item.getItemId()==R.id.track) {

                            Uri gmmIntentUri = Uri .parse("geo:0,0?q=" + Uri.encode(list.get(position).getFullAddress()));
                            Intent mi=new Intent(Intent.ACTION_VIEW,gmmIntentUri);
                            mi.setPackage("com.google.android.apps.maps");
                            mContext.startActivity(mi);
                        }
                        return false;
                    }
                });
                popup.show();
            }
        });

        Glide.with(mContext)
                .asBitmap()
                .load(list.get(position).getProfilepath())
                .into(holder.circleImageView);
    }

    public void sendSMS(final String number)
    {



        firestore.collection("AllUser").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                if (documentSnapshot.exists()) {
                    getname = documentSnapshot.getString("fname");


                    int permitionCheck = ContextCompat.checkSelfPermission(mContext, Manifest.permission.SEND_SMS);

                    if (permitionCheck == PackageManager.PERMISSION_GRANTED) {

                        String sms = getname + " Need your Blood,You can contact them";

                        SmsManager smsManager = SmsManager.getDefault();

                        smsManager.sendTextMessage(number,null,sms,null,null);

                        Toast.makeText(mContext, "Message Sent Successfully", LENGTH_SHORT).show();

                    } else {
                        ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.SEND_SMS}, 101);
                    }

                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(mContext, "Data Not Found", LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder {

        TextView fname,mNumber,bGroup;
        RelativeLayout layout;
        de.hdodenhof.circleimageview.CircleImageView circleImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            layout=itemView.findViewById(R.id.parent_layout);
            fname=itemView.findViewById(R.id.fullName);
            mNumber=itemView.findViewById(R.id.number);
            bGroup=itemView.findViewById(R.id.bloodGroup);
            circleImageView=itemView.findViewById(R.id.profileImg);
        }
        }
    }


