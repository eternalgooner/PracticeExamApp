package com.apptrumps.practiceexamapp.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by David on 03-Sep-17.
 */

public class PersonContentProvider extends ContentProvider {

    private PersonsDbHelper personDbHelper;
//    private static final UriMatcher sUriMatcher = buildUriMatcher();
//
//    private static UriMatcher buildUriMatcher() {
//        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
//
//        //add matches with addUri(String authority, String path, int code)
//        uriMatcher.addURI(PersonsContract.AUTHORITY, PersonsContract.PATH_FAV_MOVIES, FAV_MOVIES);
//        uriMatcher.addURI(FavMoviesContract.AUTHORITY, FavMoviesContract.PATH_FAV_MOVIES + FORWARD_SLASH_HASH, MOVIE_WITH_ID );
//
//        return uriMatcher;
//    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        personDbHelper = new PersonsDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final SQLiteDatabase db = personDbHelper.getReadableDatabase();
        Cursor retCursor;

        retCursor = db.query(PersonsContract.PersonsEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = personDbHelper.getWritableDatabase();
        Uri returnUri;

        long id = db.insert(PersonsContract.PersonsEntry.TABLE_NAME, null, values);
        if(id > 0){
            returnUri = ContentUris.withAppendedId(PersonsContract.PersonsEntry.CONTENT_URI, id);
        }else{
            throw new SQLException("failed to insert row into " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
