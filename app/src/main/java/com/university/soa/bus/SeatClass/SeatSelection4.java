package com.university.soa.bus.SeatClass;

/**
 * Created by pkumar on 5/7/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.university.soa.bus.BookedInfo;
import com.university.soa.bus.R;
import com.university.soa.bus.SavedSeats;
import com.university.soa.bus.book;

import java.util.ArrayList;
import java.util.List;

public class SeatSelection4 extends AppCompatActivity implements OnSeatSelected {

    private static final int COLUMNS =5;
    private TextView txtSeatSelected;
    Button mBook;
    String str_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start4);
        mBook=findViewById(R.id.button2);
        txtSeatSelected = (TextView)findViewById(R.id.txt_seat_selected);



        List<AbstractItem> items = new ArrayList<>();
        for (int i=0; i<40; i++) {

            if (i%COLUMNS==0 || i%COLUMNS==4) {
                items.add(new EdgeItem(String.valueOf(i)));
            } else if (i%COLUMNS==1 || i%COLUMNS==3) {
                items.add(new CenterItem(String.valueOf(i)));
            } else {
                items.add(new EmptyItem(String.valueOf(i)));
            }
        }

        GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lst_items);
        recyclerView.setLayoutManager(manager);

        AirplaneAdapter adapter = new AirplaneAdapter(this, items);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onSeatSelected(int count) {
        mBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(), "Please Select Seats", Toast.LENGTH_SHORT).show();
            }
        });
        if(count==0) {
            mBook.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    Toast.makeText(getApplicationContext(), "Please Select Seats", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            mBook.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {

                    // Start NewActivity.class
                    Intent myIntent = new Intent(SeatSelection4.this,
                            SavedSeats.class);
                    startActivity(myIntent);
                }
            });
        }
            }

    }

