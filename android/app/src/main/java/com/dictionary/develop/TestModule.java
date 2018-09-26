package com.dictionary.develop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;

import com.facebook.react.bridge.ReactContext;

public class TestModule {

    private DBHandler my_db_handler;

    public void init() {
        this.my_db_handler = new DBHandler(null);
        //my_db_handler.insertData("HaFeng", "beautiful");
    }

    public String name = "hieu dep trai qua";
    public String getName () {
        return this.name;
    }
    public String getData() {
        return my_db_handler.getData();
    }
}