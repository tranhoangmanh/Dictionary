package com.tranhoangmanh.dictionary.viewholders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tranhoangmanh.dictionary.R;

public class PhoneticViewHolder extends RecyclerView.ViewHolder {
    public TextView txtPhonetic;
    public ImageButton imgBtnAudio;

    public PhoneticViewHolder(@NonNull View itemView) {
        super(itemView);
        txtPhonetic = itemView.findViewById(R.id.txtPhonetic);
        imgBtnAudio = itemView.findViewById(R.id.imgBtnAudio);
    }
}
