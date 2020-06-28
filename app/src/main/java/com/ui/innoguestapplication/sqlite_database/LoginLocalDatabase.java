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
    private static LoginLocalDatabase loginLocalDatabase = null;

    private static Context context;
    public LoginDatabaseHelper getDbHelper() {
        return dbHelper;
    }

    public static LoginLocalDatabase getLoginLocalDatabase(Context ccontext){
        if(loginLocalDatabase==null){
            loginLocalDatabase = new LoginLocalDatabase( ccontext);
            context = ccontext;
        }
        return loginLocalDatabase;
    }

    private LoginLocalDatabase(Context context) {
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
            values.put(LoginDataContract.LoginEntry.COLUMN_NAME_LAN,"EN");
            values.put(LoginDataContract.LoginEntry.COLUMN_NAME_THEME, "LIGHT");
            values.put(LoginDataContract.LoginEntry.COLUMN_NAME_NOTIFICATIONS, "OFF");
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

    private void setLocalData(Language language,Theme theme,NotifySound notifySound) {


        SQLiteDatabase db = dbHelper.getWritableDatabase();

            // New value for one column
            String lan = language.toString();
            String th =theme.toString();
            String sound = notifySound.toString();
            ContentValues values = new ContentValues();
            values.put(LoginDataContract.LoginEntry.COLUMN_NAME_LAN, lan);
            values.put(LoginDataContract.LoginEntry.COLUMN_NAME_THEME, th);
        values.put(LoginDataContract.LoginEntry.COLUMN_NAME_NOTIFICATIONS, sound);

            // Which row to update, based on the title
            String selectionLan = LoginDataContract.LoginEntry.COLUMN_NAME_LAN + " LIKE ?";
            String[] selectionArgsLan = { LocalSettingsStorage.getLocalSettingsStorage(context).getLanguage().toString() };
            String selectionTheme = LoginDataContract.LoginEntry.COLUMN_NAME_THEME + " LIKE ?";
            String[] selectionArgsTheme = { LocalSettingsStorage.getLocalSettingsStorage(context).getTheme().toString() };
        String selectionSound = LoginDataContract.LoginEntry.COLUMN_NAME_NOTIFICATIONS + " LIKE ?";
        String[] selectionArgsSound = { LocalSettingsStorage.getLocalSettingsStorage(context).getSound().toString() };

            db.update(
                    LoginDataContract.LoginEntry.TABLE_NAME,
                    values,
                    selectionLan,
                    selectionArgsLan);
            db.update(
                    LoginDataContract.LoginEntry.TABLE_NAME,
                    values,
                    selectionTheme,
                    selectionArgsTheme);

            db.update(
                LoginDataContract.LoginEntry.TABLE_NAME,
                values,
                    selectionSound,
                    selectionArgsSound);





    }

    public void setLanguage(Language language){
        setLocalData(language,getTheme(),getSound());
    }
    public void setTheme(Theme theme){
        setLocalData(getLanguage(),theme,getSound());
    }
    public void setSound(NotifySound sound){
        setLocalData(getLanguage(),getTheme(),sound);

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

    public Language getLanguage(){
        Cursor cursor = getLoginDataCursor();

        try {
            String lang = "";

            cursor.moveToNext();
            lang = cursor.getString(
                    cursor.getColumnIndexOrThrow(LoginDataContract.LoginEntry.COLUMN_NAME_LAN));

            cursor.close();
            if(lang.equals("")){
                return  null;
            }

            return  Language.valueOf(lang);
        } catch (CursorIndexOutOfBoundsException | IllegalArgumentException ex) {
            return null;

        }
    }
    public Theme getTheme(){
        Cursor cursor = getLoginDataCursor();

        try {
            String theme = "";

            cursor.moveToNext();
            theme = cursor.getString(
                    cursor.getColumnIndexOrThrow(LoginDataContract.LoginEntry.COLUMN_NAME_THEME));

            cursor.close();
            if(theme.equals("")){
                return  null;
            }

            return  Theme.valueOf(theme);
        } catch (CursorIndexOutOfBoundsException | IllegalArgumentException ex) {
            return null;

        }
    }
    public NotifySound getSound(){
        Cursor cursor = getLoginDataCursor();

        try {
            String sound = "";

            cursor.moveToNext();
            sound = cursor.getString(
                    cursor.getColumnIndexOrThrow(LoginDataContract.LoginEntry.COLUMN_NAME_NOTIFICATIONS));

            cursor.close();
            if(sound.equals("")){
                return  null;
            }

            return  NotifySound.valueOf(sound);
        } catch (CursorIndexOutOfBoundsException | IllegalArgumentException ex) {
            return null;

        }
    }
    public Cursor getLoginDataCursor() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                LoginDataContract.LoginEntry.COLUMN_NAME_EMAIL,
                LoginDataContract.LoginEntry.COLUMN_NAME_PASSWORD,
                LoginDataContract.LoginEntry.COLUMN_NAME_LAN,
                LoginDataContract.LoginEntry.COLUMN_NAME_THEME,
                LoginDataContract.LoginEntry.COLUMN_NAME_NOTIFICATIONS
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
