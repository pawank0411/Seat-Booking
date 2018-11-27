package com.university.soa.bus.seatclass;

/**
 * Created by pkumar on 5/7/18.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    static Set<Integer> positions;
    // private TextView txtSeatSelected;
    Button mBook;
    TextView time;
    Toast mToast;
    RelativeLayout loading;
    int bookCount = 0;
    String str_empcode;
    AirplaneAdapter adapter;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;
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

        ref = database.getReference();
        mBook = findViewById(R.id.button2);
        time = findViewById(R.id.show);
        loading = findViewById(R.id.loading);

        mBook.setText(R.string.button2);

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
                    showToast(positions.size() + "seats selected");
                    /*edit.putStringSet(str_empcode, positions);
                    edit.commit();*/
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
        positions = new HashSet<>();
        adapter = new AirplaneAdapter(this, items, positions);
        getDataFromFirebase(info);
        RecyclerView recyclerView = findViewById(R.id.lst_items);
        int spaceInPixels = 0;
        recyclerView.addItemDecoration(new RecyclerViewItemDecorator(spaceInPixels));
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    private void showToast(String message) {
        if (mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        mToast.show();
    }

    private void getDataFromFirebase(final BookingInfo info) {
        final List<BookingInfo> bookings = new ArrayList<>();
        loading.setVisibility(View.VISIBLE);
        DatabaseReference bookingRef = ref.child("booked seats");
        bookingRef.orderByChild("Route").equalTo(info.tour_name)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot shot : dataSnapshot.getChildren()) {
                            BookingInfo bookInfo = new BookingInfo();
                            String seatss = shot.child("Seats").getValue(String.class);
                            String newSeats = seatss.replace("[","")
                                    .replace("]","");
                            String[] seats = newSeats.split(",");
                            for (String seat: seats) {
                                try {
                                    bookInfo.seats.add(Integer.parseInt(seat));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            bookInfo.tour_name = shot.child("Route").getValue(String.class);
                            bookInfo.date = shot.child("Journey Date").getValue(String.class);
                            bookInfo.timing = shot.child("Timmings").getValue(String.class);
                            if (bookInfo != null && bookInfo.timing.equals(info.timing) &&
                                    bookInfo.date.equals(info.date))
                                bookings.add(bookInfo);
                        }
                        convertBookingToSeats(bookings);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("SeatSelection", "Error " + databaseError.getMessage());
                    }
                });
    }

    private void convertBookingToSeats(List<BookingInfo> infos) {
        Log.e("SeatSelection", "Returned " + infos.toString());
        List<Integer> seats = new ArrayList<>();
        if (infos != null) {
            for (BookingInfo info: infos) {
                seats.addAll(info.seats);
            }
        }
        Log.e("SeatSelection", "Formatted " + seats.toString());
        positions = new HashSet<>(seats);
        adapter.updateSelected(new HashSet<>(seats));
        loading.setVisibility(View.GONE);
    }

    @Override
    public void onSeatSelected(Set<Integer> selected, List<Integer> seats) {
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