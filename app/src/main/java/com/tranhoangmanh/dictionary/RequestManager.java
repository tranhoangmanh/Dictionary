package com.tranhoangmanh.dictionary;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tranhoangmanh.dictionary.models.APIResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RequestManager {
    Context context;
    //Hỗ trợ convert tốt hơn
    Gson gson = new GsonBuilder().setLenient().create();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    ProgressDialog progressDialog;

    public RequestManager(Context context, ProgressDialog progressDialog) {
        this.context = context;
        this.progressDialog = progressDialog;
    }

    public void getWordMeaning(OnFetchDataListener listener, String word)
    {
        CallDictionary callDictionary = retrofit.create(CallDictionary.class);
        Call<List<APIResponse>> call = callDictionary.callMeanings(word);

        try{
            call.enqueue(new Callback<List<APIResponse>>() {
                @Override
                public void onResponse(Call<List<APIResponse>> call, Response<List<APIResponse>> response) {
                    if(!response.isSuccessful())
                    {
                        Toast.makeText(context, "Không tìm thấy từ " + word, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        return;
                    }

                    listener.onFetchData(response.body().get(0), response.message());
                }

                @Override
                public void onFailure(Call<List<APIResponse>> call, Throwable t) {
                    listener.onError("Lỗi truy xuất dữ liệu!!!");
                }
            });
        }catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(context, "Đã có lỗi xảy ra!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public interface CallDictionary{
        @GET("entries/en/{word}")
        Call<List<APIResponse>> callMeanings(
                @Path("word") String word
        ); //Abstract class
    }
}
