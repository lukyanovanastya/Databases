package main;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class DatabaseMap extends HashMap<String,DatabaseNew> {

    public void read(String file) throws Exception{
        Gson gson = new Gson();
        DatabaseMap dbMap = gson.fromJson(new FileReader(file), DatabaseMap.class);
    }

    public void write(String file) throws Exception{
        Gson gson = new Gson();
        gson.toJson(new FileWriter(file));
    }

    public Set<String> getTables(String name){
        return this.get(name).keySet();
    }
}
