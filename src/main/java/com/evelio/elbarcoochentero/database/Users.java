package com.evelio.elbarcoochentero.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Users extends SQLiteOpenHelper {

    String sqlCreate = "CREATE TABLE Users (nick TEXT PRIMARY KEY, password TEXT)";
    public Users(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
        db.execSQL("INSERT INTO Users VALUES ('Ucam' , '1234')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int lastVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL(sqlCreate);
        db.execSQL("INSERT INTO Users VALUES ('Ucam' , '1234')");
    }
}
