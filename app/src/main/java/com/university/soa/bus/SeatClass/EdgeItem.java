package com.university.soa.bus.SeatClass;

/**
 * Created by pkumar on 5/6/18.
 */

public class EdgeItem extends AbstractItem {

    private boolean selectable;

    public EdgeItem(String label) {
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
        return AbstractItem.TYPE_EDGE;
    }

}