package com.example.moofyread;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    //declare db
    private static final String DATABASE_NAME = "MoofyRead.db";

    //declare table favourite
    private static final String FAV_TABLE_NAME = "favourites";
    private static final String FAV_COLUMN_DOC_ID = "fav_id";
    private static final String FAV_COLUMN_DOC_NAME = "doc_name";
    private static final String FAV_COLUMN_DOC_AUTHOR = "doc_author";

    //constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    //create database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table favourites (fav_id integer primary key autoincrement, doc_name text, doc_author text)"
        );
    }

    //upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS favourites");
        onCreate(db);
    }

    //insert data
    public boolean insertFav (String doc_name, String doc_author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("doc_name", doc_name);
        contentValues.put("doc_author", doc_author);

        long result = db.insert("favourites", null, contentValues);
        if (result == -1)
            return false;
            //Toast.makeText(context, "Insert Failed", Toast.LENGTH_SHORT).show();
        else
            return true;
            //Toast.makeText(context, "Insert Successful", Toast.LENGTH_SHORT).show();
    }

    //retrieve all data
    public Cursor readAllData(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = null;
        if (db != null){
            res = db.rawQuery("select * from favourites", null);
        }

        return res;
    }

}
