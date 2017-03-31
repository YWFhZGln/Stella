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
 * Created by aman on 28/3/17.
 */

public class CenterAdapter extends ArrayAdapter<Center> {


    public CenterAdapter(@NonNull Context context, @LayoutRes int resource, List<Center> centerList) {
        super(context, 0,centerList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView=convertView;
        if (listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.center_list,parent,false);

        }

        Center currentCenter=getItem(position);

        TextView nameTextView=(TextView)listItemView.findViewById(R.id.center_name);
        nameTextView.setText(currentCenter.getName());

        TextView addressView=(TextView)listItemView.findViewById(R.id.center_address);
        addressView.setText(currentCenter.getAddress());

        TextView sno=(TextView)listItemView.findViewById(R.id.sno);
        sno.setText(String.valueOf(currentCenter.getSno()));

        return listItemView;
    }
}
