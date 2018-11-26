package com.university.soa.bus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import models.AppStatus;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        String str_empcode, a, b;

        EditText edt_empcode;

        Button newuser;

        Button login,showtkt;


        LinearLayout ll;
        TextView t1;
        RelativeLayout Rl;

        AppStatus appStatus;
        ProgressDialog pr;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);
                t1 = findViewById(R.id.seat);
                ll = findViewById(R.id.layout1);
                Rl = findViewById(R.id.home);
                ll.setVisibility(View.VISIBLE);
                t1.setVisibility(View.VISIBLE);
                Rl.setVisibility(View.GONE);

                appStatus = new AppStatus(getApplicationContext());
                login = findViewById(R.id.btn_login);
                showtkt = findViewById(R.id.btn_ticket);
                edt_empcode = findViewById(R.id.CodeNum);

                showtkt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,Showticket.class);
                        intent.putExtra("empcode",edt_empcode.getText().toString());
                        startActivity(intent);
                    }
                });


            pr=new ProgressDialog(MainActivity.this);
            pr.setMessage("Safe life, Happy life");
            pr.setCancelable(false);
            pr.show();

            Runnable progressRunnable = new Runnable() {

                @Override
                public void run() {
                    pr.cancel();
                }
            };

            Handler pdCanceller = new Handler();
            pdCanceller.postDelayed(progressRunnable, 3000);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.logout, menu);
                return true;
        }

        @Override
        public void onClick(View v) {
            if (appStatus.isOnline()) {
                str_empcode = edt_empcode.getText().toString().trim();
                        SharedPreferences preferences = getSharedPreferences("MYPREFS", MODE_PRIVATE);
                        //try {
                                if (str_empcode.length() == 0) {
                                    Toast.makeText(getApplicationContext(),
                                            "Please enter your Employee Code",
                                            Toast.LENGTH_LONG).show();
                                }
                                else {
                                    if (str_empcode.equals("1234") || str_empcode.equals("0000")
                                            || str_empcode.equals("1111") || str_empcode.equals("1891")) {
                                        // pr.dismiss();
                                        Toast.makeText(this, "Welcome User",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent1 = new Intent(MainActivity.this, Book.class);
                                        startActivity(intent1);
                                    }
                                    else {
                                        Toast.makeText(this, "Please get registered your " +
                                                "employee code.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        //} catch (Exception e) {

                                //Toast.makeText(getApplicationContext(), "We did not find any account with the given Employee Code in this device", Toast.LENGTH_LONG).show();
                        //}
                } else {

                        Toast.makeText(getApplicationContext(), "Please see that you have Active internet connection..", Toast.LENGTH_LONG).show();
                }
        }

        @Override
        public void onBackPressed() {
                moveTaskToBack(true);
        }
}


