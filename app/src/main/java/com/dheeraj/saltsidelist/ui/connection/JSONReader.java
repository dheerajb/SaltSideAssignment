package com.dheeraj.saltsidelist.ui.connection;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class JSONReader implements DataReader {
    ResultReceiver mCallback;


    private static final String TAG = JSONReader.class.getSimpleName();
    Context mContext;


    public JSONReader(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void requestData(String url, ResultReceiver callback) {
        mCallback = callback;
        fetchJson(url);
    }

    private void fetchJson(String url) {

        JsonArrayRequest jsonRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        mCallback.onReadComplete(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Something went wrong: " + error.getMessage());
                mCallback.onReadComplete(null);
            }
        });

        Volley.newRequestQueue(mContext).add(jsonRequest);
    }
}
