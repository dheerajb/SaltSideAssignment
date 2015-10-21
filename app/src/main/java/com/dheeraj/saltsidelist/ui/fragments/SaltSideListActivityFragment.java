package com.dheeraj.saltsidelist.ui.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dheeraj.saltsidelist.R;
import com.dheeraj.saltsidelist.ui.adapter.DataSet;
import com.dheeraj.saltsidelist.ui.adapter.SaltSideListAdapter;
import com.dheeraj.saltsidelist.ui.connection.JSONReader;
import com.dheeraj.saltsidelist.ui.connection.DataReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;

public class SaltSideListActivityFragment extends Fragment implements AbsListView.OnItemClickListener {
    private static final String TAG = SaltSideListActivityFragment.class.getSimpleName();
    private ProgressDialog mDialog;
    SaltSideListAdapter mAdapter;
    ListView mList;
    private OnSaltFragmentListener mListener;
    ArrayList<DataSet> mItemsArray;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnSaltFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnSaltFragmentListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        /*Running out of time, will see orientation changes later*/
        super.onCreate(savedInstanceState);
        mAdapter = new SaltSideListAdapter(getActivity().getApplicationContext(), R.layout.salt_list_item, null);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saltside_list, container, false);
        mList = (ListView) view.findViewById(R.id.json_list);
        mList.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDialog = new ProgressDialog(getActivity());
        mDialog.setMessage("Please wait...");
        mDialog.setCancelable(false);
        mDialog.show();
        JSONReader jsonReader;
        jsonReader = new JSONReader(getActivity());
        jsonReader.requestData("https://gist.githubusercontent.com/maclir/f715d78b49c3b4b3b77f/raw/161f315b0964491db8e5fa5046156eff032ab747/items.json", new DataReader.ResultReceiver() {
            @Override
            public void onReadComplete(JSONArray result) {

                if (result != null) {
                    Log.d(TAG, result.toString());
                    Gson gson = new Gson();
                    mItemsArray = gson.fromJson(result.toString(), new TypeToken<ArrayList<DataSet>>() {
                    }.getType());

                    mAdapter = new SaltSideListAdapter(getActivity().getApplicationContext(), R.layout.salt_list_item, mItemsArray);
                    mList.setAdapter(mAdapter);
                }
                mDialog.cancel();
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            DataSet data = mItemsArray.get(position);
            mListener.onSaltListFragment(data);
        }
    }

    public interface OnSaltFragmentListener {
        public void onSaltListFragment(DataSet data);
    }
}
