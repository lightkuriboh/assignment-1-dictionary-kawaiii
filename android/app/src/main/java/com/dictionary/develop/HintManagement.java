package com.dictionary.develop;

import java.util.ArrayList;

public class HintManagement {
    private ArrayList<String> myListWord = new ArrayList<>();

    public void addMoreWord(String word) {
        this.myListWord.add(word);
    }

    public void initTrie () {

    }

    public String getHint(String word) {
        return "";
    }

}
