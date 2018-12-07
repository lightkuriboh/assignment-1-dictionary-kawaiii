package com.dictionary.develop;

public class WordDetailManagement {
    public static String getDecoration(String originalWord, String details) {
        String listDetails = "";
        String noteDetail = originalWord + "\n";
        Integer cur = 0;
        Integer i = -1;
        boolean nowExample = false;
        String curWord = "";
        char chr = 'm';
        char lst_chr;
        while (i < details.length()-1) {
            i++;
            lst_chr = chr;
            chr = details.charAt(i);
            if (chr == '*' && lst_chr == '\n') {
                cur = 1;
                nowExample = false;
                curWord = "";
                continue;
            }
            if (chr == '-' && lst_chr == '\n') {
                cur = 2;
                curWord = "";
                nowExample = false;
                continue;
            }
            if (chr == '=' && lst_chr == '\n') {
                cur = 3;
                curWord = "";
                continue;
            }
            if (chr == '@' && lst_chr == '\n') {
                cur = 4;
                curWord = "Dạng khác: ";
                continue;
            }
            if (cur == 0) {
                noteDetail = noteDetail + chr;
                continue;
            }
            if (chr != '\n') {
                curWord = curWord + chr;
                continue;
            }
            if (cur == 3) {
                if (!nowExample) {
                    nowExample = true;
                    String example = "<Text style={{fontSize: 15}}>Example:</Text>";
                    listDetails += example;
                }
                curWord = "\t" + curWord;
            }
            String myLabel = "";
            if (cur == 1) {
                myLabel = "style={{fontSize: 15; fontWeight: 'bold'; color: 'red'}}";
            }
            if (cur == 2) {
                myLabel = "style={{fontSize: 12; color: 'blue'}}";
            }
            if (cur == 3) {
                myLabel = "style={{fontSize: 15; fontWeight: 'italic'; color: 'green'}}";
            }
            if (cur == 4) {
                myLabel = "style={{fontSize: 12; fontWeight: 'bold'; color: 'orange'}}";
            }
            String line = "<Text " + myLabel + ">" + curWord + "</Text>";
            listDetails += line;
        }

        noteDetail += "\n";
        noteDetail = "<Text style={{fontSize: 15; color: 'purple'}}>" + noteDetail + "</Text>";
        listDetails += noteDetail;
        return listDetails;
    }
}
