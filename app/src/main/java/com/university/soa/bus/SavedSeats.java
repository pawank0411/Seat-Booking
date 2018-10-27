package com.university.soa.bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.AppStatus;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SavedSeats extends AppCompatActivity {


    DatabaseReference ref;


    Button Saveinfo, button;
    SharedPreferences seats;
    Set<String> selected;
    List<Integer> selectSeats = new ArrayList<>();
    String str_name, str_empcode, str_psnum, str_phnmber, emp_code,number;
    EditText Pname, Pnumber, Empcode, passnumber,editText2;
    BookingInfo info;
    TextView T1, T2;
    AppStatus appStatus;

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    private boolean mVerificationInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booked_info);

        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().hasExtra("employee")) {
            emp_code = getIntent().getStringExtra("employee");
            info = new BookingInfo();
            info = Parcels.unwrap(getIntent().getParcelableExtra("info"));


            selectSeats = info.seats;
        }

        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().hasExtra("info")) {
            info = Parcels.unwrap(getIntent().getParcelableExtra("info"));
        } else if (savedInstanceState != null && savedInstanceState.getParcelable("info") != null) {
            info = Parcels.unwrap(savedInstanceState.getParcelable("info"));
        }

        selected = new HashSet<>();
        appStatus = new AppStatus(getApplicationContext());
        Saveinfo = findViewById(R.id.saveinfo);
        button = findViewById(R.id.button3);
        Pname = findViewById(R.id.PName);
        Pnumber = findViewById(R.id.PhnNumber);
        Empcode = findViewById(R.id.EmpCode);
        passnumber = findViewById(R.id.PsNum);
        final CardView cardView=findViewById(R.id.card);
        final CardView cardView1=findViewById(R.id.cards);
        editText2 = (EditText) findViewById(R.id.editText);
        T1 = (TextView) findViewById(R.id.Opt);
        T2 = (TextView) findViewById(R.id.Details);
        seats = getSharedPreferences("seats", MODE_PRIVATE);
        selected = seats.getStringSet(emp_code, new HashSet<String>());

        ref = FirebaseDatabase.getInstance().getReference().child("booked details");


        mAuth = FirebaseAuth.getInstance();


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                // Log.d(TAG, "onVerificationCompleted:" + credential);
                mVerificationInProgress = true;
                Toast.makeText(SavedSeats.this, "Verification Complete", Toast.LENGTH_SHORT).show();
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                // Log.w(TAG, "onVerificationFailed", e);
                Toast.makeText(SavedSeats.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    Toast.makeText(SavedSeats.this,
                            "InValid Phone Number", Toast.LENGTH_SHORT).show();
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                }

            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                //Log.d(TAG, "onCodeSent:" + verificationId);
                Toast.makeText(SavedSeats.this, "Verification code has been send on your number", Toast.LENGTH_LONG).show();
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                // ...
            }
        };

        Saveinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appStatus.isOnline()) {
                    str_name = Pname.getText().toString().trim();
                    str_phnmber = Pnumber.getText().toString().trim();
                    str_empcode = Empcode.getText().toString().trim();
                    str_psnum = passnumber.getText().toString().trim();
                    try {
                        if (str_name.length() == 0 && str_empcode.length() == 0 &&
                                str_phnmber.length() == 0 && str_psnum.length() == 0)
                        {
                            Toast.makeText(getApplicationContext(), "Please fill the Details..", Toast.LENGTH_LONG).show();
                        }
                        else if (str_name.length() == 0 || str_empcode.length() == 0 ||
                                str_empcode.length() == 0 || str_empcode.length() == 0) {
                            Toast.makeText(getApplicationContext(), "All fields are Mandatory", Toast.LENGTH_LONG).show();
                        }
                        else if (str_empcode.equals(0)) {
                            Toast.makeText(getApplicationContext(), "EMPLOYEE IS MANDATORY", Toast.LENGTH_LONG).show();
                        }
                        else if (!str_empcode.equals(0) && !str_name.equals(0) &&
                                !str_phnmber.equals(0)) {
                            if (str_empcode.equals("1891")) {
                                number = "9826542127";
                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                        "+91 " + number,
                                        60,
                                        java.util.concurrent.TimeUnit.SECONDS,
                                        SavedSeats.this,
                                        mCallbacks);

                                T2.setText("Please Enter the OTP Send to Your Registered Mobile Number " + number);
                                cardView1.setVisibility(INVISIBLE);
                                cardView.setVisibility(VISIBLE);
                            } else if (str_empcode.equals("1234")) {
                                number = "8457892173";
                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                        "+91 " + number,
                                        60,
                                        java.util.concurrent.TimeUnit.SECONDS,
                                        SavedSeats.this,
                                        mCallbacks);

                                T2.setText("Please Enter the OTP Send to Your Registered Mobile Number " + number);
                                cardView1.setVisibility(INVISIBLE);
                                cardView.setVisibility(VISIBLE);
                            } else if (str_empcode.equals("0000")) {
                                number = "9165767463";
                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                        "+91 " + number,
                                        60,
                                        java.util.concurrent.TimeUnit.SECONDS,
                                        SavedSeats.this,
                                        mCallbacks);

                                T2.setText("Please Enter the OTP Send to Your Registered Mobile Number " + number);
                                cardView1.setVisibility(INVISIBLE);
                                cardView.setVisibility(VISIBLE);
                            } else if (str_empcode.equals("1111")) {
                                number = "9131341690";
                                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                        "+91 " + number,
                                        60,
                                        java.util.concurrent.TimeUnit.SECONDS,
                                        SavedSeats.this,
                                        mCallbacks);

                                T2.setText("Please Enter the OTP Send to Your Registered Mobile Number " + number);
                                cardView1.setVisibility(INVISIBLE);
                                cardView.setVisibility(VISIBLE);}
                            else {
                                Toast.makeText(SavedSeats.this, "Invalid Employee Code", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Soory,Error Occured..", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please see that you have Active internet connection..", Toast.LENGTH_LONG).show();
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, editText2.getText().toString());
                signInWithPhoneAuthCredential(credential);
                store();

            }
        });


    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(SavedSeats.this, "Verification Done", Toast.LENGTH_SHORT).show();

                        } else {
                            // Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(SavedSeats.this, "Invalid Verification", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }
    public void store() {
        str_name = Pname.getText().toString().trim();
        str_empcode = Empcode.getText().toString().trim();
        str_phnmber = Pnumber.getText().toString().trim();
        str_psnum = passnumber.getText().toString().trim();
        Log.i("Seats", "Selected: " + selected);

        Toast.makeText(SavedSeats.this,"Seat nos. "+ printSelected(selectSeats), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, TicketActivity.class);
        info.emp_name = str_name;
        info.emp_code = str_empcode;
        info.phoneNo = str_phnmber;
        info.passNo = str_psnum;
        intent.putExtra("info", Parcels.wrap(info));
        startActivity(intent);

        /**Check here*/

      /* Map<String,String> userdata=new HashMap<>();

            userdata.put("Employee name",info.emp_name);
            userdata.put("Employee code",info.emp_code);
            userdata.put("Passenger's Phone no.",info.phoneNo);
            userdata.put("Pass no.",info.passNo);
            userdata.put("Journey Date",info.date);
            userdata.put("Route",info.tour_name);
            userdata.put("Timmings",info.timing);
            userdata.put("Seats",String.valueOf(info.seats));

            ref.push().setValue(userdata);

            */

        // Toast.makeText(getApplicationContext(), "Booked Succesfully..", Toast.LENGTH_SHORT).show();
    }

    private String printSelected(List<Integer> selectedSeats) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < selectedSeats.size(); i++) {
            if (i == selectedSeats.size() - 1) {
                result.append(selectedSeats.get(i));
                result.append(".");
            } else {
                result.append(selectedSeats.get(i));
                result.append(", ");
            }
        }

        return result.toString();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}






