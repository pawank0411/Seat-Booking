package com.university.soa.bus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.university.soa.bus.SeatClass.TourSelection;
import com.university.soa.bus.TestLogin.GoogleSignInActivity;
import com.university.soa.bus.TestLogin.LoginRegistration;
import com.university.soa.bus.TestLogin.SaveData;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import Models.AppStatus;

/**
 * Created by pkumar on 5/7/18.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        String str_empcode,a,b;

        EditText edt_empcode;
        Button login, newuser;
        //boolean b=false;
        Properties prop;
        DatabaseReference ref;
        FirebaseDatabase Database;
        //private ProgressBar progressBar;
        AppStatus appStatus;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);

                prop=new Properties();
               // auth = FirebaseAuth.getInstance();
                Database = FirebaseDatabase.getInstance();
                ref = Database.getReference().child("Employee Details : ");
                appStatus=new AppStatus(getApplicationContext());
                login = (Button) findViewById(R.id.btn_login);
                newuser = (Button) findViewById(R.id.newuser);
                edt_empcode = (EditText) findViewById(R.id.CodeNum);


                newuser.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                startActivity(new Intent(MainActivity.this,LoginRegistration.class));
                        }
                });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                MenuInflater inflater=getMenuInflater();
                inflater.inflate(R.menu.logout,menu);
                return true;
        }

        @Override
        public void onClick(View v) {
                if (appStatus.isOnline()) {
                        str_empcode=edt_empcode.getText().toString().trim();
                        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                        try {
                                if (str_empcode.length()==0) {
                                        Toast.makeText(getApplicationContext(),
                                                "Please enter your Employee Code",
                                                Toast.LENGTH_LONG).show();
                                }
                                else if(str_empcode.equals("1234")){
                                        Toast.makeText(this, "Welcome User", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(MainActivity.this,book.class);
                                        startActivity(intent);

                                }
                                else if(str_empcode.equals("0000")){
                                        Toast.makeText(this, "Welcome User", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(MainActivity.this,book.class);
                                        startActivity(intent);

                                }
                                else if(str_empcode.equals("1111")){
                                        Toast.makeText(this, "Welcome User", Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(MainActivity.this,book.class);
                                        startActivity(intent);

                                }
                        }catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "We did not find any account with the given Employee Code in this device", Toast.LENGTH_LONG).show();
                        }
                } else {
                        Toast.makeText(getApplicationContext(),"Please see that you have Active internet connection..",Toast.LENGTH_LONG).show();
                }
        }

        }


