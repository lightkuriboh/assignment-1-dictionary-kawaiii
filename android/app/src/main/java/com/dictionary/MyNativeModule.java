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

public class MyNativeModule extends ReactContextBaseJavaModule {

    ArrayList<Integer> myList = new ArrayList<>();

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
        System.out.println(greeting);
        callback.invoke(greeting);
    }

    @ReactMethod
    public void initData() {
        this.myList.add(1);
        this.myList.add(4);
        this.myList.add(3);
    }

    @Override
    public String getName() {
        return "MyNativeModule";
    }
}