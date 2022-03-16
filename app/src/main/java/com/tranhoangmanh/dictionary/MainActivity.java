package com.tranhoangmanh.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.tranhoangmanh.dictionary.adapters.MeaningAdapter;
import com.tranhoangmanh.dictionary.adapters.PhoneticAdapter;
import com.tranhoangmanh.dictionary.models.APIResponse;

public class MainActivity extends AppCompatActivity {
    SearchView search_view;
    TextView txtWord;
    RecyclerView recyclerPhonetics, recyclerMeanings;
    ProgressDialog progressDialog;
    PhoneticAdapter phoneticAdapter;
    MeaningAdapter meaningAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();

        progressDialog.setTitle("Đang tải dữ liệu");
        progressDialog.show();
        RequestManager manager = new RequestManager(MainActivity.this, progressDialog);
        manager.getWordMeaning(listener, "hello");
    }

    private void addEvents() {
        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Đang tra cứu từ " + query);
                progressDialog.show();
                RequestManager manager = new RequestManager(MainActivity.this, progressDialog);
                manager.getWordMeaning(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void addControls() {
        search_view = findViewById(R.id.search_view);
        txtWord = findViewById(R.id.txtWord);
        recyclerPhonetics = findViewById(R.id.recyclerPhonetic);
        recyclerMeanings = findViewById(R.id.recyclerMeanings);
        progressDialog = new ProgressDialog(MainActivity.this);
    }

    private final OnFetchDataListener listener = new OnFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if(apiResponse == null)
            {
                Toast.makeText(MainActivity.this, "Không tìm thấy dữ liệu!", Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(APIResponse apiResponse) {
        txtWord.setText("Từ bạn tìm: " + apiResponse.getWord());
        recyclerPhonetics.setHasFixedSize(true);
        recyclerPhonetics.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        phoneticAdapter = new PhoneticAdapter(apiResponse.getPhonetics(), MainActivity.this);
        recyclerPhonetics.setAdapter(phoneticAdapter);

        recyclerMeanings.setHasFixedSize(true);
        recyclerMeanings.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        meaningAdapter = new MeaningAdapter(apiResponse.getMeanings(), MainActivity.this);
        recyclerMeanings.setAdapter(meaningAdapter);
    }
}