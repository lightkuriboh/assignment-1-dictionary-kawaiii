import java.util.ArrayList;
import java.util.Scanner;

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

        System.out.println("Enter number of words you want to search: ");
        int n = myScanner.nextInt();
        myScanner.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter word you want to search: ");
            String wordLook = myScanner.nextLine();
            System.out.println("The meaning of " + wordLook + " is \"" + this.myDictionaryManager.dictionaryLookup(wordLook) + "\"");
        }
    }
}
