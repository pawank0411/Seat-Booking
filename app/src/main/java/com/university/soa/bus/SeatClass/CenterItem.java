package com.university.soa.bus.SeatClass;

/**
 * Created by pkumar on 5/6/18.
 */

public class CenterItem extends AbstractItem {

    public CenterItem(String label) {
        super(label);
    }


    @Override
    public int getType() {
        return AbstractItem.TYPE_CENTER;
    }

}