package com.dheeraj.saltsidelist.ui.connection;

import org.json.JSONArray;

import java.util.List;

public interface DataReader {

    void requestData(String url, ResultReceiver callback);

    public static interface ResultReceiver {

        void onReadComplete(JSONArray result);
    }

}