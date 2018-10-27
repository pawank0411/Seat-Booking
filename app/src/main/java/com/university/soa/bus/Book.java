package com.university.soa.bus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.university.soa.bus.seatclass.TourSelection;

import org.parceler.Parcels;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by pkumar on 5/14/18.
 */

public class Book extends AppCompatActivity {


    Button B1, B2, B3;
    String str_empcode = "", date;
    BookingInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book);

        B1 = findViewById(R.id.button7);
        B2 = findViewById(R.id.button9);
        B3 = findViewById(R.id.button8);

        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().hasExtra("employee")) {
            info = Parcels.unwrap(getIntent().getParcelableExtra("info"));
        }

        final Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEE, MMM d, ''yy", Locale.getDefault());
        c.add(Calendar.DAY_OF_YEAR, 0);
        Date Today = c.getTime();
        String today = df.format(Today.getTime());
        B1.setText(today);
        date = today;
        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Book.this, TourSelection.class);
                intent.putExtra("employee", str_empcode + "D1");
                info = new BookingInfo();
                info.date = B1.getText().toString();
                intent.putExtra("info", Parcels.wrap(info));
                startActivity(intent);
            }
        });
        c.add(Calendar.DAY_OF_YEAR, 1);
        Date Tommorrow = c.getTime();
        String tm = df.format(Tommorrow.getTime());
        B3.setText(tm);
        date = tm;
        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Book.this, TourSelection.class);
                intent.putExtra("employee", str_empcode + "D2");
                info.date = B3.getText().toString();
                intent.putExtra("info", Parcels.wrap(info));
                startActivity(intent);
            }
        });
        c.add(Calendar.DAY_OF_YEAR, 1);
        Date Dtm = c.getTime();
        String dtm = df.format(Dtm.getTime());
        B2.setText(dtm);
        date = dtm;
        B2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Book.this, TourSelection.class);
                intent.putExtra("employee", str_empcode + "D3");
                info.date = B2.getText().toString();
                intent.putExtra("info", Parcels.wrap(info));
                startActivity(intent);
            }
        });
    }

}