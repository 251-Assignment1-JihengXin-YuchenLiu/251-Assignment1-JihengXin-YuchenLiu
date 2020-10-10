
import junit.framework.TestCase;

import javax.servlet.ServletException;
import java.io.IOException;

public class JsonServerlstTest extends TestCase {

    public void testNAME1() throws ServletException, IOException {
        assertEquals("Jihen Xin",JsonServerlst.NAME1());
    }

    public void testNumber1() throws ServletException, IOException {
        assertEquals("19029645",JsonServerlst.number1());
    }

    public void testNumber2() throws ServletException, IOException {
        assertEquals("19029755",JsonServerlst.number2());
    }
}