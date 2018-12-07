package com.dictionary;

import android.widget.Toast;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dictionary.develop.*;

public class MyNativeModule extends ReactContextBaseJavaModule {

    private DBHandler mine;

    public MyNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void showMyToast(String msg) {
        Toast.makeText(getCurrentActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @ReactMethod
    public void greetUser(String name, Callback callback) {
        String greeting = this.mine.getData(name.toLowerCase());
        callback.invoke(greeting);
    }

    @ReactMethod
    public void initData() {
        this.mine = new DBHandler(this.getReactApplicationContext());
        this.mine.initData();
    }

    @ReactMethod
    public void getHint(String word, Callback callback) {
        callback.invoke(this.mine.getHint(word.toLowerCase()));
    }

    @Override
    public String getName() {
        return "MyNativeModule";
    }
}