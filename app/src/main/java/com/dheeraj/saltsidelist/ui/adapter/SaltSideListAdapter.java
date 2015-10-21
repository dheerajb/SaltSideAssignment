package com.dheeraj.saltsidelist.ui.adapter;

import android.content.ClipData;
import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObservable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dheeraj.saltsidelist.R;


import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SaltSideListAdapter extends ArrayAdapter<DataSet> {

    private ArrayList<DataSet> objects;
    Context mContext;
    public SaltSideListAdapter(Context context, int textViewResourceId, ArrayList<DataSet> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
        mContext = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.salt_list_item, null);
        }
        DataSet dataSet = objects.get(position);

        if (dataSet != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView title = (TextView) view.findViewById(R.id.title_text);
            TextView description = (TextView) view.findViewById(R.id.description_text);
            ImageView icon = (ImageView) view.findViewById(R.id.icon_image);

            if (title != null){
                title.setText(dataSet.title);
            }
            if (description != null){
                description.setText(dataSet.description);
            }
            if (icon != null){
                Glide.with(mContext).load(dataSet.image).centerCrop().into(icon);
            }
        }

        // the view must be returned to our activity
        return view;

    }

}
