package com.example.freshcheck;

import android.database.sqlite.SQLiteDatabase;

public class Data {
    MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
    SQLiteDatabase db = dbHelper.getWritableDatabase();

    private class MyDatabaseHelper {
        public MyDatabaseHelper(Data data) {
        }

        public SQLiteDatabase getWritableDatabase() {
            return null;
        }
    }

    db.execSQL("CREATE TABLE IF NOT EXISTS my_table (id INTEGER PRIMARY KEY, name TEXT, value INTEGER)");

}
