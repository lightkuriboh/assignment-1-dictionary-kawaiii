
package com.dictionary.develop;

import android.database.Cursor;
import android.database.sqlite.*;
import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBHandler extends SQLiteAssetHelper {

    private HintManagement myHints;

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "dictionary.db";

    private static final String TABLE_NAME = "minhpro99";

    public SQLiteDatabase my_db;


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }

    public String insertData(String detail) { // detail co dang {{$english},{$vietnamese},{$pronunciation}}
        return "Inserted success!";
        //return "Word existed!";
        //return "Invalid data!";
    }

    public String deleteData(String word) {
        return "Deleted success!";
        //return "Word not found!";
    }

    public String updateWord(final String word, String detail) { // detail co dang {{$english},{$vietnamese},{$pronunciation}}
        return "Updated success!";
        //return "Word not found!";
        //return "Invalid data!";
    }

    public String getHint(String word) {
        return this.myHints.getHint(word);
    }

    public void initData() {
        this.my_db = this.getReadableDatabase();
        this.myHints = new HintManagement();
        Cursor cursor = this.my_db.rawQuery("SELECT english FROM " + TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            this.myHints.addMoreWord(cursor.getString(0));
            cursor.moveToNext();
        }
        this.myHints.initData();
    }

    public String getData(String searchWord) {
        this.my_db = this.getReadableDatabase();
        Cursor cursor = this.my_db.rawQuery("SELECT pronunciation, vietnamese FROM " + TABLE_NAME + " WHERE english = '" + searchWord + "';", null);
        String result = "Cannot fine this word \"" + searchWord + "\"";
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                result = cursor.getString(0) + "\n" + cursor.getString(1);
            }
        }
        return result;
    }
}
