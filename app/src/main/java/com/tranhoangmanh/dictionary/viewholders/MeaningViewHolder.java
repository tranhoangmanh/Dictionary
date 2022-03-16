package com.tranhoangmanh.dictionary.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tranhoangmanh.dictionary.R;

public class MeaningViewHolder extends RecyclerView.ViewHolder {
    public TextView txtPartsOfSpeech;
    public RecyclerView recyclerDefinitions;

    public MeaningViewHolder(@NonNull View itemView) {
        super(itemView);
        txtPartsOfSpeech = itemView.findViewById(R.id.txtPartsOfSpeech);
        recyclerDefinitions = itemView.findViewById(R.id.recyclerDefinitions);
    }
}
