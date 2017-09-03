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
    private static final String DROP_TABLE_IF_EXISTS_ = "DROP TABLE IF EXISTS ";
    private static final String CREATE_TABLE = "CREATE TABLE ";
    private static final String _OPEN_BRACKET = " (";
    private static final String INTEGER_PRIMARY_KEY_AUTOINCREMENT_COMMA = "INTEGER PRIMARY KEY AUTOINCREMENT,";
    private static final String _TEXT_NOT_NULL_COMMA = " TEXT NOT NULL,";
    private static final String _TEXT_NOT_NULL = " TEXT NOT NULL";
    private static final String _INTEGER_COMMA = " INTEGER,";
    private static final String _INTEGER = " INTEGER";
    private static final String CLOSE_BRACKET_SEMI_COLON = ");";


    public PersonsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_PERSONS_TABLE = CREATE_TABLE +
                PersonsContract.PersonsEntry.TABLE_NAME + _OPEN_BRACKET +
                PersonsContract.PersonsEntry._ID + INTEGER_PRIMARY_KEY_AUTOINCREMENT_COMMA +
                PersonsContract.PersonsEntry.COLUMN_NAME + _TEXT_NOT_NULL_COMMA +
                PersonsContract.PersonsEntry.COLUMN_AGE + _INTEGER_COMMA +
                PersonsContract.PersonsEntry.COLUMN_HEIGHT + _TEXT_NOT_NULL_COMMA +
                PersonsContract.PersonsEntry.COLUMN_PHONE + _INTEGER +
                CLOSE_BRACKET_SEMI_COLON;

        db.execSQL(SQL_CREATE_PERSONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_IF_EXISTS_ + PersonsContract.PersonsEntry.TABLE_NAME);
        onCreate(db);
    }
}
