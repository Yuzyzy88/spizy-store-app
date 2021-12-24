package com.example.listreminder_mid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


    public DataBaseHelper(@Nullable Context context) {
        super(context,"SpizyStore.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table history(id text, customer text, product text, quantity text, price text, total text, description text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists history");
    }

    public Boolean insert(String id, String customer, String product, String quantity,  String price, String total, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        //db.execSQL("Create table history(id text, customer text, product text, quantity text, price text, total text, description text)");
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("customer", customer);
        cv.put("product", product);
        cv.put("quantity", quantity);
        cv.put("price", price);
        cv.put("total", total);
        cv.put("description", description);
        long Insert = db.insert("history", null, cv);
        if (Insert==-1) return false;
        else return true;
    }

    public Cursor view() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from history", null);
        return cursor;
    }
}
