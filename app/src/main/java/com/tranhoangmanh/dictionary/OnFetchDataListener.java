package com.tranhoangmanh.dictionary;

import com.tranhoangmanh.dictionary.models.APIResponse;

public interface OnFetchDataListener {
    void onFetchData(APIResponse apiResponse, String message);
    void onError(String message);
}
