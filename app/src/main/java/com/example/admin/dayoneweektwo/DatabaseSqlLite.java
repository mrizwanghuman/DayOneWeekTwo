package com.example.admin.dayoneweektwo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by  Admin on 11/13/2017.
 */

public class DatabaseSqlLite extends SQLiteOpenHelper {

    public static   String DATABASE_NAME = "Celebrity";
    public static final String TABLE_NAME ="Celebrities";
    public static final int VERSION =2;
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_GENDER ="Gender";

    public DatabaseSqlLite(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("+ COLUMN_NAME +" TEXT PRIMARY KEY," + COLUMN_GENDER + " TEXT "+  ")";
sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long AddCelebrity(Celebrity celebrity){
SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,celebrity.getName());
        contentValues.put(COLUMN_GENDER, celebrity.getGender());

        long row = db.insert(TABLE_NAME,null, contentValues);
        return row;

    }
    public List<Celebrity> getCeleb(){

        List<Celebrity> celebrities = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query ="SELECT * FROM "+ TABLE_NAME;
        Cursor cursor =db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                Celebrity celebrity = new Celebrity(cursor.getString(0), cursor.getString(1));
                celebrities.add(celebrity);
            }while (cursor.moveToNext());
        }
        return celebrities;
    }
    public void deleteCeleb(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "Name='"+ name+ "'", null);

    }
    public void updateCeleb(String name, String newName){
        SQLiteDatabase db = this.getWritableDatabase();
        String updateQuery = "UPDATE "+TABLE_NAME+" SET Name='"+newName + "' WHERE Name='"+name+"'";
        db.execSQL(updateQuery);
    }



}
