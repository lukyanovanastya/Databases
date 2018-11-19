package main;
import com.google.gson.Gson;
import junit.framework.TestCase;
import main.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class MainTest extends TestCase {

    public MainTest(){
        super("MainTest");
    }

    public void testWriteBase()throws Exception{
        DatabaseMap map = new DatabaseMap();
        Database db = map.dbCreate("DB1");

        Table t = db.tableCreate("t1", null );
        t.columnAdd("c1", "CHAR");
        t.columnAdd("i1", "INTEGER");
        t.valuesAdd('a',1);
        t.valuesAdd('b',2);
        t.valuesAdd('c',3);

        t = db.tableCreate("t2", null );
        t.columnAdd("s1", "STRING");
        t.valuesAdd('a');
        t.valuesAdd('b');
        t.valuesAdd('c');

        //System.out.println(map);
        //write to file...
        map.write("./src/test/java/main/testWriteBase-out.json");
    }
    /*
    public void testReadBase()throws Exception{
        DatabaseMap map = DatabaseMap.read("./src/test/java/main/test3.json");
        System.out.println(map);
        //write to file...
        map.write("./src/test/java/main/test3-out.json");
        System.out.println("done");
    }
    */

}
