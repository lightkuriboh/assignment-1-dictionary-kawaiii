package sample.WordTranslate.Trie;
import java.util.ArrayList;

public class Trie {
    private final int limitNumWordEachNode = 30;
    private TrieNode rootNode;

    public Trie() {
        this.rootNode = new TrieNode();
    }

    public void insertWord(final int index, final String word) {
        TrieNode curNode = this.rootNode;
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            if ('a' <= curChar && curChar <= 'z') {
                TrieNode nextNode = curNode.getNextNode(curChar);
                if (nextNode == null) {
                    nextNode = curNode.newNode(curChar);
                }
                curNode = nextNode;
                curNode.addIndex(index, this.limitNumWordEachNode);
            }
        }
    }

    public ArrayList<Integer> searchWord(final String word) {
        TrieNode curNode = this.rootNode;
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            if ('a' <= curChar && curChar <= 'z') {
                TrieNode nextNode = curNode.getNextNode(curChar);
                if (nextNode != null) {
                    curNode = nextNode;
                } else {
                    break;
                }
            }
        }
        return curNode.getIndexList();
    }
}
