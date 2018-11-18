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
        DatabaseMap map = DatabaseMap.read("./src/test/java/main/test2.json");
        System.out.println(map);
        //write to file...
        map.write("./src/test/java/main/test2-out.json");
        System.out.println("done");
    }

}
