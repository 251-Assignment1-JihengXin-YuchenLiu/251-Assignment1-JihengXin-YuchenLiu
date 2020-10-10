import javax.swing.text.BadLocationException;
import java.util.ArrayList;
//the function how to select the spefic word.

public class Search {//search specific word
    public static ArrayList<ArrayList<Integer>> search(String text, String keyword) throws BadLocationException {
        text=text.replaceAll("\n","");
        int index = text.indexOf(keyword);
        ArrayList<ArrayList<Integer>> i_indexList= new ArrayList<ArrayList<Integer>>();
        while (index != -1 & !keyword.equals("") ) {
            ArrayList<Integer> j_indexList= new ArrayList<Integer>();
            j_indexList.add(index);
            j_indexList.add(index + keyword.length());
            i_indexList.add(j_indexList);
            index=text.indexOf(keyword, index + keyword.length());
        }
        return i_indexList;
    }
}