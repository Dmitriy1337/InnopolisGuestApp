package com.ui.innoguestapplication.sqlite_database;

import android.provider.BaseColumns;

public class NotificationDatabaseContract {
    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NotificartionEntry.TABLE_NAME + " (" +
                    NotificartionEntry._ID + " INTEGER PRIMARY KEY," +
                    NotificartionEntry.COLUMN_NOTIFICATION + " TEXT," +
                    NotificartionEntry.COLUMN_TIME +" TEXT,"+
                    NotificartionEntry.COLUMN_DESCR +  " TEXT)";

    static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NotificartionEntry.TABLE_NAME;

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private NotificationDatabaseContract() {}

    /* Inner class that defines the table contents */
    public static class NotificartionEntry implements BaseColumns {
        public static final String TABLE_NAME = "notification_table";
        public static final String COLUMN_NOTIFICATION = "notification";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_DESCR = "descr";

    }
}
