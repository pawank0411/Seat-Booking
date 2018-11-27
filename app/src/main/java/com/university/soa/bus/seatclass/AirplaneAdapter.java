package com.university.soa.bus.seatclass;

/*
 * Created by pkumar on 5/6/18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.university.soa.bus.R;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AirplaneAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {

    private OnSeatSelected mOnSeatSelected;
    private Set<Integer> selected = new HashSet<>(), positions;

    private static class EdgeViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        private final ImageView imgSeatSelected;


        EdgeViewHolder(View itemView) {
            super(itemView);
            imgSeat = itemView.findViewById(R.id.img_seat);
            imgSeatSelected = itemView.findViewById(R.id.img_seat_selected);

        }

    }

    private static class CenterViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSeat;
        private final ImageView imgSeatSelected;

        CenterViewHolder(View itemView) {
            super(itemView);
            imgSeat = itemView.findViewById(R.id.img_seat);
            imgSeatSelected = itemView.findViewById(R.id.img_seat_selected);


        }

    }

    private static class EmptyViewHolder extends RecyclerView.ViewHolder {

        EmptyViewHolder(View itemView) {
            super(itemView);
        }

    }

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private List<AbstractItem> mItems;

    public AirplaneAdapter(Context context, List<AbstractItem> items, Set<Integer> selected) {
        mOnSeatSelected = (OnSeatSelected) context;
        mContext = context;
        positions = selected;
        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
    }

    public void updateSelected(Set<Integer> selected) {
        positions = selected;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == AbstractItem.TYPE_CENTER) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new CenterViewHolder(itemView);
        } else if (viewType == AbstractItem.TYPE_EDGE) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_seat, parent, false);
            return new EdgeViewHolder(itemView);
        } else {
            View itemView = new View(mContext);
            return new EmptyViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        int type = mItems.get(position).getType();
        if (type == AbstractItem.TYPE_CENTER) {
            final CenterItem item = (CenterItem) mItems.get(position);
            CenterViewHolder holder = (CenterViewHolder) viewHolder;
            if(null != SeatSelection.positions && SeatSelection.positions.contains(position) && !selected.contains(position) ) {
                item.setSelectable(false);
                holder.imgSeatSelected.setVisibility(View.VISIBLE);

            }


            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(item.isSelectable()) {
                        toggleSelection(position);
                        if (SeatSelection.positions != null) {
                            if (isSelected(position)) {
                                SeatSelection.positions.add(position);
                                selected.add(position);
                                positions.add(position);
                            } else if (SeatSelection.positions.contains(position)) {
                                SeatSelection.positions.remove(position);
                                selected.remove(position);
                                positions.remove(position);
                            }
                        }
                    }
                    else {
                        Toast.makeText(mContext, "Seat already booked", Toast.LENGTH_SHORT).show();
                    }
                    mOnSeatSelected.onSeatSelected(positions, getSelectedItems());
                }
            });


            holder.imgSeatSelected.setVisibility(isSelected(position)||!item.isSelectable() ? View.VISIBLE : View.GONE);

        } else if (type == AbstractItem.TYPE_EDGE) {
            final EdgeItem item = (EdgeItem) mItems.get(position);
            EdgeViewHolder holder = (EdgeViewHolder) viewHolder;
            if(null != SeatSelection.positions && SeatSelection.positions.contains(position) && !selected.contains(position) ) {
                item.setSelectable(false);
                holder.imgSeatSelected.setVisibility(View.VISIBLE);

            }


            holder.imgSeat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(item.isSelectable()) {
                        toggleSelection(position);
                        if (isSelected(position)) {
                            SeatSelection.positions.add(position);
                            selected.add(position);
                            positions.add(position);
                        } else if (SeatSelection.positions.contains(position)) {
                            SeatSelection.positions.remove(position);
                            selected.remove(position);
                            positions.remove(position);
                        }
                    }
                    else {
                        Toast.makeText(mContext, "Seat already booked", Toast.LENGTH_SHORT).show();

                    }
                    mOnSeatSelected.onSeatSelected(positions, getSelectedItems());
                }
            });

            holder.imgSeatSelected.setVisibility(isSelected(position)||!item.isSelectable() ? View.VISIBLE : View.GONE);

        }
    }

}