package com.tranhoangmanh.dictionary.adapters;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tranhoangmanh.dictionary.R;
import com.tranhoangmanh.dictionary.models.Phonetics;
import com.tranhoangmanh.dictionary.viewholders.PhoneticViewHolder;

import java.util.List;

public class PhoneticAdapter extends RecyclerView.Adapter<PhoneticViewHolder> {
    List<Phonetics> phoneticsList;
    Context context;

    public PhoneticAdapter(List<Phonetics> phoneticsList, Context context) {
        this.phoneticsList = phoneticsList;
        this.context = context;
    }

    @NonNull
    @Override
    public PhoneticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View customView = LayoutInflater.from(parent.getContext()).inflate(R.layout.phonetic_list_items, parent, false);
        return new PhoneticViewHolder(customView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneticViewHolder holder, int position) {
        Phonetics phonetic = phoneticsList.get(position);
        String source = phonetic.getAudio();
        if(!phonetic.getText().equals(""))
        {
            holder.txtPhonetic.setText(phonetic.getText());
        }
//        else
//        {
//            holder.txtPhonetic.setVisibility(View.GONE);
//        }
        if(source.equals("")) {
            holder.imgBtnAudio.setImageResource(R.drawable.ic_volume_off);
        }

        holder.imgBtnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer player = new MediaPlayer();
                try{
                    if(source.equals(""))
                    {
                        Toast.makeText(context, "Không có âm thanh cho ví dụ này!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        player.setDataSource(source);
                        player.prepare();
                        player.start();
                    }
                }catch (Exception ex)
                {
                    Log.e("Lỗi ", ex.toString());
                    Toast.makeText(view.getContext(), "Không thể mở âm thanh!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(phoneticsList == null)
        {
            return 0;
        }
        return phoneticsList.size();
    }
}
