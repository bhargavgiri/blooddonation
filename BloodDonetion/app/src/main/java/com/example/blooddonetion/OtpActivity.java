package com.example.blooddonetion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {

    CountryCodePicker ccp;
    private EditText mPhoneText;
    private EditText mCodeText;
    private TextView mErrorText;
    private Button mSendBtn;
    private FirebaseAuth mAuth;
    private String smobile;
    private int btnType=0;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;




   // String sfname,slname,spincode,sweight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);


        mPhoneText=(EditText)findViewById(R.id.reg_phone);
        mCodeText=(EditText)findViewById(R.id.reg_otp);
        mSendBtn=(Button)findViewById(R.id.reg_phone_btn);
        mErrorText=findViewById(R.id.errorText);
        ccp=(CountryCodePicker)findViewById(R.id.ccp);
        mAuth=FirebaseAuth.getInstance();
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnType==0) {
                    mPhoneText.setEnabled(false);
                    mSendBtn.setEnabled(false);
                    String phoneNumber =ccp.getFullNumberWithPlus()+ mPhoneText.getText().toString();
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            phoneNumber,
                            60,
                            TimeUnit.SECONDS,
                            OtpActivity.this,
                            mCallbacks

                    );
                }else {
                    mSendBtn.setEnabled(false);
                    String verificationCode=mCodeText.getText().toString();
                    PhoneAuthCredential
                            credential=PhoneAuthProvider.getCredential(mVerificationId,verificationCode);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });
        mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }
            @Override
            public void onVerificationFailed(FirebaseException e) {
                mErrorText.setText("there was some error in login ");
                mErrorText.setVisibility(View.VISIBLE);
            }
            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
// Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                btnType=1;
                mPhoneText.setVisibility(View.INVISIBLE);
                ccp.setVisibility(View.INVISIBLE);
                mSendBtn.setText("Verify code");
                mSendBtn.setEnabled(true);
// ...
            }
        };
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
// Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = task.getResult().getUser();
                            smobile=ccp.getFullNumberWithPlus()+mPhoneText.getText().toString();
                         /*   sfname=getIntent().getStringExtra("fname");
                            slname=getIntent().getStringExtra("lname");
                            spincode=getIntent().getStringExtra("pincode");
                            sweight=getIntent().getStringExtra("weight");*/

                            Intent mainIntent=new Intent(OtpActivity.this,CreateAccount.class);

                          /*  mainIntent.putExtra("ssfname",sfname);
                            mainIntent.putExtra("sslname",slname);
                            mainIntent.putExtra("sspincode",spincode);
                            mainIntent.putExtra("ssweight",sweight);
                            mainIntent.putExtra("ssmobile",smobile);*/

                            startActivity(mainIntent);

// ...
                        } else {
// Sign in failed, display a message and update the UI
// Log.w(TAG, "signInWithCredential:failure", task.getException());
                            mErrorText.setText("there was some error in loggin in.");
                            mErrorText.setVisibility(View.VISIBLE);
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
// The verification code entered was invalid
                            }
                        }
                    }
                });
    }
}



