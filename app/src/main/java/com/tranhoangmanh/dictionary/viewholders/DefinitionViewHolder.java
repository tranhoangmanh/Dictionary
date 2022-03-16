package com.tranhoangmanh.dictionary.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tranhoangmanh.dictionary.R;

public class DefinitionViewHolder extends RecyclerView.ViewHolder {
    public TextView txtDefinition, txtExample, txtSynonyms, txtAntonyms;

    public DefinitionViewHolder(@NonNull View itemView) {
        super(itemView);
        txtDefinition = itemView.findViewById(R.id.txtDefinition);
        txtExample = itemView.findViewById(R.id.txtExample);
        txtSynonyms = itemView.findViewById(R.id.txtSynonyms);
        txtAntonyms = itemView.findViewById(R.id.txtAntonyms);
    }
}
