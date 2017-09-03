package com.apptrumps.practiceexamapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.apptrumps.practiceexamapp.utils.MockUtils;

public class MainActivity extends AppCompatActivity implements PersonAdapter.ListItemClickListener{
    private RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvList = (RecyclerView) findViewById(R.id.rv_people);

        PersonAdapter adapter = new PersonAdapter(MockUtils.getPeopleList(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(layoutManager);
        rvList.setAdapter(adapter);

        DividerItemDecoration divider = new DividerItemDecoration(rvList.getContext(), layoutManager.getOrientation());
        rvList.addItemDecoration(divider);
    }

    @Override
    public void onLIstItemClick(int clickedItem) {
        Log.d("MAIN", "clicked position: " + clickedItem);
    }
}
