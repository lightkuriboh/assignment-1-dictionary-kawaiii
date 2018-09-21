public class Main {

    public static void main(String[] args) {
	    System.out.println("Hello");
        DictionaryCommandLine program = new DictionaryCommandLine();
//        program.dictionaryBasic();
        program.dictionaryAdvanced();
    }

}
// Nhap vao lenh co 3 dang: Lookup+" "+word để tra word, Delete+" "+word để xóa word khỏi từ điển, Search+"  "+word để tìm các từ có tiền tố giống word.