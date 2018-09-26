
package com.dictionary.develop;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.*;
import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import javax.annotation.Nullable;

public class DBHandler extends SQLiteAssetHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "edict.db";
    //private static final String DATABASE_NAME = "my_db.db";

    //private static final String TABLE_NAME = "my_table";
    private static final String TABLE_NAME = "tbl_edict";

    private static final String KEY_NAME = "name";

    private static final String KEY_DETAILS = "details";

    public SQLiteDatabase my_db;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public void insertData(String name, String details) {
        this.my_db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_DETAILS, details);
        this.my_db.insert(TABLE_NAME, null, values);
        this.my_db.close();
    }

    public String getData() {
        this.my_db = this.getReadableDatabase();
        Cursor cursor = this.my_db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        String name = cursor.getString(0);
        String details = cursor.getString(1);
        String details_2 = cursor.getString(2);
        return name + " is " + details + " xxx " + details_2;
    }
}
