package com.example.a.varetoplayer;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import static android.R.attr.checkable;
import static android.R.attr.data;
import static android.R.attr.drawable;

/**
 * Created by A on 09/10/2016.
 */

public class ScanAdaptor extends ArrayAdapter <ScanAdaptor.RowObj> {
    private final boolean[] mCheckState;

    public ScanAdaptor(Context context, List<RowObj> list) {
        super(context, R.layout.scan_row , list);
        mCheckState = new boolean[list.size()];
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //ToDo
        ViewHolder holder;

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.scan_row, parent, false);

            holder = new ViewHolder((TextView) convertView.findViewById(R.id.listTitle),(CheckBox) convertView.findViewById(R.id.cbCheckListItem),(ImageView) convertView.findViewById(R.id.listImage));

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        if (getItem(position)!= null){
            holder.title.setText(getItem(position).str);
            holder.img.setImageResource(getItem(position).drw);
            holder.checked.setChecked(mCheckState[position]);
        }

        return convertView;
    }

    

    public static class RowObj
    {
        String str;
        int drw;
        boolean chk;

        public RowObj(String str, int drw, boolean chk) {
            this.str = str;
            this.drw = drw;
            this.chk = chk;
        }
    }

}

