package com.ui.innoguestapplication.sqlite_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class LoginLocalDatabase {
    private String email = "";
    private String passwordHash = "";
    private LoginDatabaseHelper dbHelper;

    public LoginDatabaseHelper getDbHelper() {
        return dbHelper;
    }

    public LoginLocalDatabase(Context context) {
        dbHelper = new LoginDatabaseHelper(context);

    }

    public void setLoginData(LoginData loginData) {

        LoginData currentLoginData = getLoginDataOrNull();
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if(currentLoginData==null){
            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(LoginDataContract.LoginEntry.COLUMN_NAME_EMAIL, loginData.getEmail());
            values.put(LoginDataContract.LoginEntry.COLUMN_NAME_PASSWORD, loginData.getPasswordHash());

            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.insert(LoginDataContract.LoginEntry.TABLE_NAME, null, values);
        }
        else{
            // New value for one column
            String email = loginData.getEmail();
            String password = loginData.getPasswordHash();
            ContentValues values = new ContentValues();
            values.put(LoginDataContract.LoginEntry.COLUMN_NAME_EMAIL, email);
            values.put(LoginDataContract.LoginEntry.COLUMN_NAME_PASSWORD, password);

            // Which row to update, based on the title
            String selectionMail = LoginDataContract.LoginEntry.COLUMN_NAME_EMAIL + " LIKE ?";
            String[] selectionArgsMail = { currentLoginData.getEmail() };
            String selectionPass = LoginDataContract.LoginEntry.COLUMN_NAME_PASSWORD + " LIKE ?";
            String[] selectionArgsPass = { currentLoginData.getPasswordHash() };

             db.update(
                    LoginDataContract.LoginEntry.TABLE_NAME,
                    values,
                    selectionMail,
                    selectionArgsMail);
            db.update(
                    LoginDataContract.LoginEntry.TABLE_NAME,
                    values,
                    selectionPass,
                    selectionArgsPass);

        }





    }



    public LoginData getLoginDataOrNull() {

        Cursor cursor = getLoginDataCursor();

        try {
            String email = "";
            String pass = "";
            cursor.moveToNext();
            email = cursor.getString(
                    cursor.getColumnIndexOrThrow(LoginDataContract.LoginEntry.COLUMN_NAME_EMAIL));
            pass = cursor.getString(
                    cursor.getColumnIndexOrThrow(LoginDataContract.LoginEntry.COLUMN_NAME_PASSWORD));
            cursor.close();
            if(email.equals("")){
                return  null;
            }

            return new LoginData(email, pass);
        } catch (CursorIndexOutOfBoundsException | IllegalArgumentException ex) {
            return null;

        }

    }

    private Cursor getLoginDataCursor() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                LoginDataContract.LoginEntry.COLUMN_NAME_EMAIL,
                LoginDataContract.LoginEntry.COLUMN_NAME_PASSWORD
        };


// How you want the results sorted in the resulting Cursor
        String sortOrder =
                LoginDataContract.LoginEntry.COLUMN_NAME_EMAIL + " DESC";

        return db.query(
                LoginDataContract.LoginEntry.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );
    }


}
