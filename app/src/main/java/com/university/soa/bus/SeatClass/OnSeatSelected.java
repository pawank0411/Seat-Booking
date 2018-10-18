package com.university.soa.bus.SeatClass;

import java.util.Set;

/**
 * Created by pkumar on 5/6/18.
 */

public interface OnSeatSelected {

    void onSeatSelected(int count, Set<String> selected);
}