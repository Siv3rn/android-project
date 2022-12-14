package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;


    public DBManager(Context con){
        context = con;
    }
    public DBManager open() throws SQLException{
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    public void insert(String name, String gender, String prodi, String address){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME,name);
        contentValues.put(DatabaseHelper.GENDER,gender);
        contentValues.put(DatabaseHelper.PRODI,prodi);
        contentValues.put(DatabaseHelper.ADDRESS,address);

        database.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }
    public Cursor fetch(){
        String[] columns = new String[]{DatabaseHelper._ID,DatabaseHelper.NAME,DatabaseHelper.GENDER,DatabaseHelper.PRODI,DatabaseHelper.ADDRESS};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,columns,null,null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String gender, String prodi, String address){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME,name);
        contentValues.put(DatabaseHelper.GENDER,gender);
        contentValues.put(DatabaseHelper.PRODI,prodi);
        contentValues.put(DatabaseHelper.ADDRESS,address);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;


    }
    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
