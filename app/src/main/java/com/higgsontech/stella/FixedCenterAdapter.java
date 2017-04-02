package com.higgsontech.stella;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aman on 2/4/17.
 */

public class FixedCenterAdapter extends ArrayAdapter<Center> {
    public FixedCenterAdapter(@NonNull Context context, @LayoutRes int resource, List<Center> fixed) {
        super(context, 0,fixed);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.fixed_center_list, parent, false);

        }

        Center currentCenter = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.center_name);
        nameTextView.setText(currentCenter.getName());

        TextView addressView = (TextView) listItemView.findViewById(R.id.center_address);
        addressView.setText(currentCenter.getAddress());

        TextView sno = (TextView) listItemView.findViewById(R.id.sno);
        TextView room = (TextView) listItemView.findViewById(R.id.roomno);
        TextView scap = (TextView) listItemView.findViewById(R.id.scap);
        TextView freq = (TextView) listItemView.findViewById(R.id.freq);
        TextView type = (TextView) listItemView.findViewById(R.id.type);
        sno.setText(String.valueOf(currentCenter.getSno()));
        room.setText("Total Rooms:"+String.valueOf(currentCenter.getNoOfRooms()));
        scap.setText("Student Capacity:"+String.valueOf(currentCenter.getStudentCapacity()));
        freq.setText("Faculty Required:"+String.valueOf(currentCenter.getFacultyCapacity()));
        type.setText("Center Type:"+currentCenter.getStudentType());


        return listItemView;
    }
}
