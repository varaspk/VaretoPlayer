package com.example.a.varetoplayer;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    TextView title;
    CheckBox checked;
    ImageView img;

    public ViewHolder(TextView title, CheckBox checked, ImageView img) {
        this.title = title;
        this.checked = checked;
        this.img = img;
    }
}