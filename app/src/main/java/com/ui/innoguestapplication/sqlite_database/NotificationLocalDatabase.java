package com.ui.innoguestapplication.sqlite_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;

public class NotificationLocalDatabase  {

    private NotificationDataHelper dbHelper;

    public NotificationDataHelper getDbHelper() {
        return dbHelper;
    }



    public NotificationLocalDatabase(Context context) {
        dbHelper = new NotificationDataHelper(context);

    }



    public void addData(Notification notification) {



        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(NotificationDatabaseContract.NotificartionEntry.COLUMN_NOTIFICATION, notification.getText());
            values.put(NotificationDatabaseContract.NotificartionEntry.COLUMN_TIME, notification.getDate_or_time());
        values.put(NotificationDatabaseContract.NotificartionEntry.COLUMN_DESCR, notification.getDescription());
            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(NotificationDatabaseContract.NotificartionEntry.TABLE_NAME, null, values);
        }









    public ArrayList<Notification> getDataOrNull() {

        ArrayList<Notification> notificationList = new ArrayList<>();
        Cursor cursor = getDataCursor();

        try {
            String notification = "";
            String time = "";
            String descr = "";
            while (cursor.moveToNext()){

                notification = cursor.getString(
                        cursor.getColumnIndexOrThrow(NotificationDatabaseContract.NotificartionEntry.COLUMN_NOTIFICATION));
            time = cursor.getString(
                    cursor.getColumnIndexOrThrow(NotificationDatabaseContract.NotificartionEntry.COLUMN_TIME));
                descr = cursor.getString(
                        cursor.getColumnIndexOrThrow(NotificationDatabaseContract.NotificartionEntry.COLUMN_DESCR));
            if (!notification.equals("")) {
                notificationList.add(new Notification(notification, time,descr));
            }

            }
            cursor.close();
            return notificationList;


        } catch (CursorIndexOutOfBoundsException | IllegalArgumentException ex) {
            return null;

        }
    }


    public Cursor getDataCursor() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                NotificationDatabaseContract.NotificartionEntry.COLUMN_NOTIFICATION,
                NotificationDatabaseContract.NotificartionEntry.COLUMN_TIME,
                NotificationDatabaseContract.NotificartionEntry.COLUMN_DESCR

        };


// How you want the results sorted in the resulting Cursor
        String sortOrder =
                NotificationDatabaseContract.NotificartionEntry.COLUMN_TIME + " DESC";

        return db.query(
                NotificationDatabaseContract.NotificartionEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
    }
}
