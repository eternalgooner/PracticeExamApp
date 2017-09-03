package com.apptrumps.practiceexamapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by David on 03-Sep-17.
 */

public class PersonsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "persons.db";
    private static final int DATABASE_VERSION = 1;


    public PersonsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
