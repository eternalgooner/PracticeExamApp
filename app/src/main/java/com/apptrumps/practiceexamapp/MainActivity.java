package com.apptrumps.practiceexamapp;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.apptrumps.practiceexamapp.db.PersonsContract;
import com.apptrumps.practiceexamapp.model.Person;
import com.apptrumps.practiceexamapp.utils.MockUtils;

import java.util.ArrayList;

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

        addEntriesToDb();
    }

    private void addEntriesToDb() {
        ContentValues[] cvArray = new ContentValues[17];
        ArrayList<Person> personList = MockUtils.getPeopleList();
        int counter = 0;

        Log.d("MainActivity", "personList size is: " + personList.size());
        for(Person p : personList){
            ContentValues contentValues = new ContentValues();
            contentValues.put(PersonsContract.PersonsEntry.COLUMN_NAME, p.getName());
            contentValues.put(PersonsContract.PersonsEntry.COLUMN_AGE, p.getAge());
            contentValues.put(PersonsContract.PersonsEntry.COLUMN_HEIGHT, p.getHeight());
            contentValues.put(PersonsContract.PersonsEntry.COLUMN_PHONE, p.getPhoneNumber());

            cvArray[counter] = contentValues;
            ++counter;
        }
        for(ContentValues value : cvArray){
            Log.d("Main", value.get("name") + "");
        }
        getContentResolver().bulkInsert(PersonsContract.PersonsEntry.CONTENT_URI, cvArray);
    }

    @Override
    public void onLIstItemClick(int clickedItem) {
        Log.d("MAIN", "clicked position: " + clickedItem);

    }
}
