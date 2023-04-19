package com.example.freshcheck;

public class Data {
    MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
    SQLiteDatabase db = dbHelper.getWritableDatabase();

    db.execSQL("CREATE TABLE IF NOT EXISTS my_table (id INTEGER PRIMARY KEY, name TEXT, value INTEGER)");

}
