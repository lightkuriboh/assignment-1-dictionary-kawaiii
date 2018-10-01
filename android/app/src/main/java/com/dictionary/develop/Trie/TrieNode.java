package com.dictionary.develop.Trie;
import java.util.ArrayList;

public class TrieNode {
    private ArrayList<Integer> indexList;
    private ArrayList<TrieNode> childList;

    public TrieNode() {
        this.indexList = new ArrayList<>();
        this.childList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            this.childList.add(null);
        }
    }

    TrieNode getNextNode(char _char) {
        int ascii = _char;
        int _a = 'a';
        ascii -= _a;
        return this.childList.get(ascii);
    }

    TrieNode newNode(int myIndex, int limit, char _char) {
        int ascii = _char;
        int _a = 'a';
        ascii -= _a;
        TrieNode myNewNode = new TrieNode();
        this.childList.set(ascii, myNewNode);
        myNewNode.addIndex(myIndex, limit);
        return myNewNode;
    }

    public ArrayList<Integer> getIndexList() {
        return this.indexList;
    }

    public void addIndex(int _index, int _limit) {
        if (this.indexList.size() < _limit) {
            this.indexList.add(_index);
        }
    }
}
