import junit.framework.TestCase;

import javax.swing.text.BadLocationException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


//the function how to select the spefic word.
public class SearchTest1 extends TestCase {

    public void testSearch() {
        String testtext="poipoikj";
        String keyword="p";
        ArrayList<ArrayList<Integer>>list1=new ArrayList<ArrayList<Integer>>();

        try {
            list1=Search.search(testtext,keyword);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        assertEquals(keyword,testtext.substring(list1.get(0).get(0),list1.get(0).get(1)));
    }
}