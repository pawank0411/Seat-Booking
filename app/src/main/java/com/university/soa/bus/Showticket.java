package com.university.soa.bus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import org.parceler.Parcels;

import java.util.HashMap;
import java.util.Map;

public class Showticket extends AppCompatActivity {

    DatabaseReference ref;
    FirebaseAuth mAuth;
    String data;
    private TextView title, tour, dateTime, seatNo, phoneNo, passNo, empName, empCode;
    private Button bookAgain, close;
    private BookingInfo info = new BookingInfo();
    SharedPreferences ticket;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        title = findViewById(R.id.title);
        tour = findViewById(R.id.tour_name);
        dateTime = findViewById(R.id.date_time);
        seatNo = findViewById(R.id.seat_no);
        phoneNo = findViewById(R.id.phone_no);
        passNo = findViewById(R.id.pass_no);
        empName = findViewById(R.id.emp_name);
        empCode = findViewById(R.id.emp_code);
        bookAgain = findViewById(R.id.book_again);
        close = findViewById(R.id.close_logout);

        ticket = getSharedPreferences("ticket",MODE_PRIVATE);
        //Use empcode to retrive the data form firebase accordingly
        Intent intent = getIntent();
        String empcode = intent.getStringExtra("empcode");


        ref = FirebaseDatabase.getInstance().getReference().child("booked details");


        mAuth = FirebaseAuth.getInstance();

            /*ref.child(empcode).child("-LS0DmmbJJhIh9FTzbxq").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String Journey = dataSnapshot.child("Journey Date").getValue().toString();
                    String employee_name = dataSnapshot.child("Employee name").getValue().toString();
                    String Passnum = dataSnapshot.child("Pass Number").getValue().toString();
                    String phn = dataSnapshot.child("Passenger's Phone Number").getValue().toString();
                    String route = dataSnapshot.child("Route").getValue().toString();
                    String seats = dataSnapshot.child("Seats").getValue().toString();
                    String timmings = dataSnapshot.child("Timmings").getValue().toString();

                    empName.setText(employee_name);
                    tour.setText(Journey);

                    passNo.setText(Passnum);
                    phoneNo.setText(phn);
                    dateTime.setText(timmings);
                    seatNo.setText(seats);
                    title.setText(route);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            */


            // Retrive the data here from hashmap
        Gson gson = new Gson();

        Map<String,Object> map = new HashMap<String,Object>();
        map = (Map<String,Object>) gson.fromJson(
                ticket.getString("ticket",data), map.getClass());

        }
    }
