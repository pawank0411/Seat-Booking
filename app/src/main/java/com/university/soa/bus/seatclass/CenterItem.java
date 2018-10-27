package com.university.soa.bus.seatclass;

/**
 * Created by pkumar on 5/6/18.
 */

public class CenterItem extends AbstractItem {

    private boolean selectable;

    public CenterItem(String label) {
        super(label);
        selectable=true;
    }

    boolean isSelectable() {

        return selectable;
    }

    void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }
    @Override
    public int getType() {
        return AbstractItem.TYPE_CENTER;
    }

}