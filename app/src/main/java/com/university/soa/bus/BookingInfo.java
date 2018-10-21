package com.university.soa.bus;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class BookingInfo {
    public String emp_name = "";
    public String emp_code = "";
    public String tour_name = "";
    public String timing = "";
    public String date = "";
    public List<Integer> seats = new ArrayList<>();
    public String phoneNo = "";
    public String passNo = "";
}
