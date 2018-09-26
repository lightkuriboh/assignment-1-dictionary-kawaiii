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

    ArrayList<Integer> myList = new ArrayList<>();
    DBHandler mine;

    public MyNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void showMyToast(String msg) {
        Toast.makeText(getCurrentActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @ReactMethod
    public void greetUser(String name, Callback callback) {
        String greeting = "Hello " + name + " ";
        this.myList.add(8);
        for (int num: this.myList) {
            greeting += Integer.toString(num);
        }
        //System.out.println(greeting);
        greeting += this.mine.getData();
        callback.invoke(greeting);
    }

    @ReactMethod
    public void initData() {
        this.mine = new DBHandler(this.getReactApplicationContext());
    }

    @Override
    public String getName() {
        return "MyNativeModule";
    }
}