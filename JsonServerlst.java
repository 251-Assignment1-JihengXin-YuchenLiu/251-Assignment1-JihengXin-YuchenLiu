package guavaExamples;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Extract our information from the JSON file
public class JsonServerlst extends HttpServlet {
    public static String service() throws ServletException, IOException {
        File jsonFile = new File("Aboutus.json");
        FileReader fileReader = new FileReader(jsonFile);
        String jsonStr = "";
        Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        jsonStr = sb.toString();
        JSONObject json1 = JSON.parseObject(jsonStr);
        java.lang.String name1 = (String) json1.get("name1");
        java.lang.String name2 = (String) json1.get("name2");
        java.lang.String number1 = (String) json1.get("number1");
        java.lang.String number2 = (String) json1.get("number2");
        return name1;
    }
    public static String NAME1() throws ServletException, IOException{
        File jsonFile = new File("Aboutus.json");
        FileReader fileReader = new FileReader(jsonFile);
        String jsonStr = "";
        Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");

        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        jsonStr = sb.toString();
        JSONObject json1 = JSON.parseObject(jsonStr);
        java.lang.String name2 = (String) json1.get("name2");
        return name2;
        }
    public static String number1() throws ServletException, IOException{
        File jsonFile = new File("Aboutus.json");
        FileReader fileReader = new FileReader(jsonFile);
        String jsonStr = "";
        Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        jsonStr = sb.toString();
        JSONObject json1 = JSON.parseObject(jsonStr);
        java.lang.String number1 = (String) json1.get("number1");
        return number1;
    }

    public static String number2() throws ServletException, IOException{
        File jsonFile = new File("Aboutus.json");
        FileReader fileReader = new FileReader(jsonFile);
        String jsonStr = "";
        Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        jsonStr = sb.toString();
        JSONObject json1 = JSON.parseObject(jsonStr);
        java.lang.String number2 = (String) json1.get("number2");
        return number2;
    }






}
