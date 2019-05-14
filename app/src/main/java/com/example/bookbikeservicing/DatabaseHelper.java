package com.example.bookbikeservicing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="register.db";
    public static final String TABLE_NAME="registeruser";
    public static final String COL_1="ID";
    public static final String COL_2="name";
    public static final String COL_3="email";
    public static final String COL_4="phonenumber";
    public static final String COL_5="address";
    public static final String COL_6="username";
    public static final String COL_7="password";
    public static final String COL_8="spinner";


    public DatabaseHelper( Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT," +
                " phonenumber NUMBER, address TEXT, username TEXT, password TEXT, spinner TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
          sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
          onCreate(sqLiteDatabase);
    }
    public long addUser(String name, String email, String phonenumber, String address, String username, String password, String spinner ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("fullname",name);
        contentValues.put("email",email);
        contentValues.put("phone_number",phonenumber);
        contentValues.put("address",address);
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("spinner",spinner);
        long res = db.insert("registeruser",null,contentValues);
        db.close();
        return res;

    }


    public Boolean checkuser( String username, String password) {

        SQLiteDatabase db = getReadableDatabase();
        String selection =  COL_6 + "=?" + " and " + COL_7 + "=?";
        String[] selectionArgs = { username, password};
        Cursor cursor = db.query( TABLE_NAME, null, selection, selectionArgs, null, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return true;
        else
            return false;
    }


}
