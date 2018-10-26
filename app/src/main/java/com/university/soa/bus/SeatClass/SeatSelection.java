package com.university.soa.bus.SeatClass;

/**
 * Created by pkumar on 5/7/18.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.university.soa.bus.BookingInfo;
import com.university.soa.bus.R;
import com.university.soa.bus.SavedSeats;
import com.university.soa.bus.utils.RecyclerViewItemDecorator;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SeatSelection extends AppCompatActivity implements OnSeatSelected {

    private static final int COLUMNS = 5;
    static Set<String> positions;
    // private TextView txtSeatSelected;
    Button mBook;
    TextView time;
    Toast mToast;
    int bookCount = 0;
    String str_empcode;
    SharedPreferences seats;
    SharedPreferences.Editor edit;
    List<Integer> selectedSeats = new ArrayList<>();
    BookingInfo info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if (getIntent() != null && getIntent().getExtras() != null
                && getIntent().hasExtra("employee")) {
            str_empcode = getIntent().getStringExtra("employee");
            info = new BookingInfo();
            info = Parcels.unwrap(getIntent().getParcelableExtra("info"));
        }

        mBook = findViewById(R.id.button2);
        time = findViewById(R.id.show);
        seats = getSharedPreferences("seats", MODE_PRIVATE);
        positions = new HashSet<>(seats.getStringSet(str_empcode, new HashSet<String>()));
        //  time.setText(R.string.show);
        mBook.setText(R.string.button2);
        //   txtSeatSelected = (TextView)findViewById(R.id.txt_seat_selected);
        List<AbstractItem> items = new ArrayList<>();
        for (int i = 0; i < 40; i++) {

            if (i % COLUMNS == 0 || i % COLUMNS == 4) {
                items.add(new EdgeItem(String.valueOf(i)));
            } else if (i % COLUMNS == 1 || i % COLUMNS == 3) {
                items.add(new CenterItem(String.valueOf(i)));
            } else {
                items.add(new EmptyItem(String.valueOf(i)));
            }
        }

        mBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (bookCount == 0) {
                    showToast("Please Select Seats");
                } else {
                    edit = seats.edit();
                    showToast(positions.size() + "seats selected");
                    edit.putStringSet(str_empcode, positions);
                    edit.commit();
                    // Start NewActivity.class
                    Intent myIntent = new Intent(SeatSelection.this,
                            SavedSeats.class);
                    myIntent.putExtra("employee", str_empcode);
                    /*myIntent.putIntegerArrayListExtra("seats",
                            (ArrayList<Integer>) selectedSeats);*/
                    info.seats = selectedSeats;
                    myIntent.putExtra("info", Parcels.wrap(info));
                    startActivity(myIntent);
                }
            }
        });

        GridLayoutManager manager = new GridLayoutManager(this, COLUMNS);

        RecyclerView recyclerView = findViewById(R.id.lst_items);
        int spaceInPixels = 0;
        recyclerView.addItemDecoration(new RecyclerViewItemDecorator(spaceInPixels));
        recyclerView.setLayoutManager(manager);

        AirplaneAdapter adapter = new AirplaneAdapter(this, items, positions);
        recyclerView.setAdapter(adapter);
    }

    private void showToast(String message) {
        if (mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    @Override
    public void onSeatSelected(Set<String> selected, List<Integer> seats) {
        bookCount = seats.size();
        positions = selected;
        selectedSeats = seats;
        if (seats.size() == 1) {
            showToast(seats.size() + "seat selected");
        } else {
            showToast(seats.size() + "seats selected");
        }
    }

}