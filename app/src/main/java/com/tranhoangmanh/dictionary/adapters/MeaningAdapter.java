package com.tranhoangmanh.dictionary.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tranhoangmanh.dictionary.R;
import com.tranhoangmanh.dictionary.models.Meanings;
import com.tranhoangmanh.dictionary.viewholders.MeaningViewHolder;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningViewHolder> {
    List<Meanings> meaningsList;
    Context context;
    DefinitionAdapter definitionAdapter;

    public MeaningAdapter(List<Meanings> meaningsList, Context context) {
        this.meaningsList = meaningsList;
        this.context = context;
    }

    @NonNull
    @Override
    public MeaningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View customView = LayoutInflater.from(parent.getContext()).inflate(R.layout.meanings_list_items, parent, false);
        return new MeaningViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeaningViewHolder holder, int position) {
        Meanings meaning = meaningsList.get(position);
        holder.txtPartsOfSpeech.setText("Parts of Speech: " + meaning.getPartOfSpeech());

        holder.recyclerDefinitions.setHasFixedSize(true);
        holder.recyclerDefinitions.setLayoutManager(new GridLayoutManager(context, 1)); //1 cột

        definitionAdapter = new DefinitionAdapter(meaning.getDefinitions(), context);
        holder.recyclerDefinitions.setAdapter(definitionAdapter);
    }

    @Override
    public int getItemCount() {
        if(meaningsList == null)
        {
            return 0;
        }
        return meaningsList.size();
    }
}
