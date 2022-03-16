package com.tranhoangmanh.dictionary.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tranhoangmanh.dictionary.R;
import com.tranhoangmanh.dictionary.models.Definitions;
import com.tranhoangmanh.dictionary.viewholders.DefinitionViewHolder;

import java.util.List;

public class DefinitionAdapter extends RecyclerView.Adapter<DefinitionViewHolder> {
    List<Definitions> definitionsList;
    Context context;

    public DefinitionAdapter(List<Definitions> definitionsList, Context context) {
        this.definitionsList = definitionsList;
        this.context = context;
    }

    @NonNull
    @Override
    public DefinitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View customView = LayoutInflater.from(context).inflate(R.layout.definitions_list_items, parent, false);
        return new DefinitionViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionViewHolder holder, int position) {
        Definitions definition = definitionsList.get(position);
        holder.txtDefinition.setText("Definition: " + definition.getDefinition());
        holder.txtExample.setText("Example: " + definition.getExample());

        StringBuilder synonymsBuilder = new StringBuilder();
        StringBuilder antonymsBuilder = new StringBuilder();

        synonymsBuilder.append(definitionsList.get(position).getSynonyms());
        antonymsBuilder.append(definitionsList.get(position).getAntonyms());

        holder.txtSynonyms.setText(synonymsBuilder);
        holder.txtAntonyms.setText(antonymsBuilder);

        holder.txtSynonyms.setSelected(true);
        holder.txtAntonyms.setSelected(true);
    }

    @Override
    public int getItemCount() {
        if(definitionsList.isEmpty())
        {
            return 0;
        }
        return definitionsList.size();
    }
}
