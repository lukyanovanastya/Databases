package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class DatabaseMap extends HashMap<String,DatabaseNew> {

    private static DatabaseMap instance = new DatabaseMap();

    public static DatabaseMap getInstance(){return instance;}

    public static DatabaseMap read(String file) throws Exception{
        //Gson gson = new Gson();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Column.class, new ColumnDeserializer());
        Gson gson = gsonBuilder.create();
        return gson.fromJson(new FileReader(file), DatabaseMap.class);
    }

    public void write(String file) throws IOException {
        //Gson gson = new Gson();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try( Writer w = new FileWriter(file) ) {
            gson.toJson(this, w);
        }
    }
}
