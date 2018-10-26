package com.university.soa.bus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;

public class TicketActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView title, tour, dateTime, seatNo, phoneNo, passNo, empName, empCode;
    private Button bookAgain, close;
    private BookingInfo info = new BookingInfo();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket);
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

        info = new BookingInfo();
        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().hasExtra("info")) {
            info = Parcels.unwrap(getIntent().getParcelableExtra("info"));
        } else if (savedInstanceState != null && savedInstanceState.getParcelable("info") != null) {
            info = Parcels.unwrap(savedInstanceState.getParcelable("info"));
        }

        bookAgain.setOnClickListener(this);
        close.setOnClickListener(this);
        assert info != null;
        setViews(info);
    }


    private void setViews(BookingInfo info) {
        if (info.seats.size() == 1) {
            title.setText(String.format(getString(R.string.booked_string), info.seats.size(), ""));
        } else {
            title.setText(String.format(getString(R.string.booked_string), info.seats.size(), "s"));
        }
        tour.setText(info.tour_name);
        dateTime.setText(String.valueOf(info.date + "    " + info.timing));
        empName.setText(String.format(getString(R.string.employee_name_string), info.emp_name));
        empCode.setText(String.format(getString(R.string.employee_code_string), info.emp_code));
        phoneNo.setText(String.format(getString(R.string.phone_number_string), info.phoneNo));
        if (TextUtils.isEmpty(info.passNo))
            passNo.setVisibility(View.GONE);
        else {
            passNo.setText(String.format(getString(R.string.pass_number_string), info.passNo));
            passNo.setVisibility(View.VISIBLE);
        }
        seatNo.setText(String.format(getString(R.string.seat_number_string),
                printSeats(info.seats)));
    }

    private String printSeats(List<Integer> seats) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < seats.size(); i++) {
            if (i == seats.size() - 1) {
                result.append(seats.get(i));
            } else {
                result.append(seats.get(i));
                result.append(", ");
            }
        }

        return result.toString();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.book_again:

                break;
            case R.id.close_logout:

                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("info", Parcels.wrap(info));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        info = Parcels.unwrap(savedInstanceState.getParcelable("info"));
        super.onRestoreInstanceState(savedInstanceState);
    }
}
