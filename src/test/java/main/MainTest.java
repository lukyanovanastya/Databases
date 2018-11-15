package main;
import com.google.gson.Gson;
import junit.framework.TestCase;
import main.*;

import java.io.FileReader;
import java.util.ArrayList;

public class MainTest extends TestCase {

    public MainTest(){
        super("MainTest");
    }

    public void testReadBase()throws Exception{
        Gson gson = new Gson();
        DatabaseMap dbMap = gson.fromJson(new FileReader("./src/test/java/main/test.json"), DatabaseMap.class);
        System.out.println("done");
    }

    public void testMain(){
        String[] test = { "Chrome", "Firefox", "Internet Explorer", "Safari",
                "Opera", "Morrowind", "Oblivion", "NFS", "Half Life 2",
                "Hitman", "Morrowind", "Oblivion", "NFS", "Half Life 2"
        };

        bases = new ArrayList<>();

        for (String s: test)
        {
            bases.put(s, null);
        }

        for (Database d: bases)
        {
            dlm.addElement(d);
        }
    }
}
