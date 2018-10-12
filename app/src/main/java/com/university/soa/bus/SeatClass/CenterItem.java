package com.university.soa.bus.SeatClass;

/**
 * Created by pkumar on 5/6/18.
 */

public class CenterItem extends AbstractItem {

    boolean selectable;
    public CenterItem(String label) {
        super(label);
        selectable=true;
    }

    public boolean isSelectable() {

        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }
    @Override
    public int getType() {
        return AbstractItem.TYPE_CENTER;
    }

}