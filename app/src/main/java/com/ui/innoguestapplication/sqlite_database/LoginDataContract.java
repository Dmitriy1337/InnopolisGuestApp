package com.ui.innoguestapplication.sqlite_database;

import android.provider.BaseColumns;

public class LoginDataContract {
     static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LoginEntry.TABLE_NAME + " (" +
                    LoginEntry._ID + " INTEGER PRIMARY KEY," +
                    LoginEntry.COLUMN_NAME_EMAIL + " TEXT," +
                    LoginEntry.COLUMN_NAME_PASSWORD + " TEXT,"+
                    LoginEntry.COLUMN_NAME_LAN + " TEXT,"+
                    LoginEntry.COLUMN_NAME_THEME + " TEXT,"+
                    LoginEntry.COLUMN_NAME_NOTIFICATIONS + " TEXT,"+
                    LoginEntry.COLUMN_NAME_TOKEN + " TEXT)";

     static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LoginEntry.TABLE_NAME;

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private LoginDataContract() {}

    /* Inner class that defines the table contents */
    public static class LoginEntry implements BaseColumns {
        public static final String TABLE_NAME = "data_table";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_LAN = "lan";
        public static final String COLUMN_NAME_THEME = "theme";
        public static final String COLUMN_NAME_NOTIFICATIONS = "notifications";
        public static final String COLUMN_NAME_TOKEN = "token";

    }
}
