package com.university.soa.bus.seatclass;

import java.util.List;
import java.util.Set;

/**
 * Created by pkumar on 5/6/18.
 */

public interface OnSeatSelected {

    void onSeatSelected(Set<String> selected, List<Integer> seats);
}