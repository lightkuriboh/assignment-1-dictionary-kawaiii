import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class DictionaryCommandLine {

    private DictionaryManagement myDictionaryManager;

    public DictionaryCommandLine() {
        this.myDictionaryManager = new DictionaryManagement();
    }

    private void showAllWords () {
        System.out.println("No   | English                         | Vietnamese");
        ArrayList<Word> myList = this.myDictionaryManager.getDictionaryData();
        for (int i = 0; i < myList.size(); i++) {
            Word word = myList.get(i);
            System.out.print(i);
            System.out.println("    | " + word.getWord_target() + "                         | " + word.getWord_explain());
        }
    }

    public void dictionaryBasic () {
        this.myDictionaryManager.insertFromCommandLine();
        this.showAllWords();
    }

    public void dictionaryAdvanced () {

        this.myDictionaryManager.insertFromFile();
        this.showAllWords();

        Scanner myScanner = new Scanner(System.in);

        System.out.println("Enter number of operators you need: ");
        int n = myScanner.nextInt();
        myScanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter your operator: ");
            String wordLook = myScanner.nextLine();
            if (wordLook.substring(0, 6).equals("Lookup")) {
                wordLook = wordLook.substring(7, wordLook.length());
                System.out.println("The meaning of " + wordLook + " is \"" + this.myDictionaryManager.dictionaryLookup(wordLook) + "\"");
            } else if (wordLook.substring(0, 6).equals("Search")) {
                wordLook = wordLook.substring(7, wordLook.length());
                ArrayList<Word> sc= this.dictionarySearcher(wordLook);
                System.out.print("Words starting with \"" + wordLook + "\" are: ");
                for(Word j: sc) {
                    System.out.print(j.getWord_target()+" ");
                }
                System.out.println(".");

            } else {
                wordLook = wordLook.substring(7, wordLook.length());
                this.myDictionaryManager.deleteWord(wordLook);
                System.out.println("The word \"" + wordLook + "\" has been deleted!");
            }
        }
        this.myDictionaryManager.dictionaryExportToFile();
    }

    public ArrayList<Word> dictionarySearcher(String curWord) {
        return this.myDictionaryManager.findPrefix(curWord);
    }
}
