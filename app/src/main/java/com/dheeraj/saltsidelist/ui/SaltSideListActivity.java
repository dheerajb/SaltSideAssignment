package com.dheeraj.saltsidelist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.dheeraj.saltsidelist.R;
import com.dheeraj.saltsidelist.ui.adapter.DataSet;
import com.dheeraj.saltsidelist.ui.fragments.SaltSideListActivityFragment;
import com.dheeraj.saltsidelist.ui.fragments.DetailsFragment;

public class SaltSideListActivity extends AppCompatActivity implements SaltSideListActivityFragment.OnSaltFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saltside_list);
    }

    @Override
    public void onSaltListFragment(DataSet data) {
        Bundle args = new Bundle();
        /*No time to not using Parcelable*/
        args.putString(DetailsFragment.ARG_PARAM1, data.title);
        args.putString(DetailsFragment.ARG_PARAM2, data.description);
        args.putString(DetailsFragment.ARG_PARAM3, data.image);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("details", args);
        startActivity(intent);
    }
}
