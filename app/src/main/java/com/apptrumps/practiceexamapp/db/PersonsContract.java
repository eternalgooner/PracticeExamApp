package com.apptrumps.practiceexamapp.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by David on 03-Sep-17.
 */

public class PersonsContract {

    public static final String AUTHORITY = "com.apptrumps.practiceexamapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PERSONS = "persons";

    private PersonsContract(){

    }

    public static final class PersonsEntry implements BaseColumns{

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PERSONS).build();

        public static final String TABLE_NAME = "persons";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AGE = "age";
        public static final String COLUMN_HEIGHT = "height";
        public static final String COLUMN_PHONE = "phone";

    }
}
