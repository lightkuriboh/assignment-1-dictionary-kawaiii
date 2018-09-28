
package com.dictionary.develop;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.*;
import android.content.Context;
import android.text.method.HideReturnsTransformationMethod;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteAssetHelper {

    private HintManagement myHints;

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

    public String getHint(String word) {
        return this.myHints.getHint(word);
    }

    public void initData() {
        this.my_db = this.getReadableDatabase();
        this.myHints = new HintManagement();
        Cursor cursor = this.my_db.rawQuery("SELECT word FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            this.myHints.addMoreWord(cursor.getString(0));
            cursor.moveToNext();
        }
        this.myHints.initTrie();
    }

    public String getData(String searchWord) {
        this.my_db = this.getReadableDatabase();
        Cursor cursor = this.my_db.rawQuery("SELECT detail FROM " + TABLE_NAME + " WHERE word = '" + searchWord + "';", null);
        String result = "Cannot fine this word \"" + searchWord + "\"";
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = cursor.getString(0);
            }
        }
        return result;
    }
}
