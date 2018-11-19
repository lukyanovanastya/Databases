package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Set;


public class DatabaseMap extends ArrayList<Database> {

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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try( Writer w = new FileWriter(file) ) {
            gson.toJson(this, w);
        }
    }

    public void add(String name){
        for(Database d: this){
            if(d.name.equals(name)) throw new RuntimeException("DB with this name already exists");
        }
        this.add(new Database(name));
        System.out.println(""+this.size());
    }

    public void remove(String name){
        for(Database d: this){
            if(d.name.equals(name)) this.remove(d);
        }
    }

    public Database dbCreate(String name) throws DBError {
        assertNotExists(name);
        Database db = new Database(name);
        this.add(db);
        return db;
    }

    /*
    public void rename(String nameOld, String nameNew){
        for(Database d: this){
            if(d.name.equals(nameOld)) {d.name=nameNew; return;}
        }
    }
    */
    void assertExists(String name) throws DBError{
        if(indexOf(name)==-1)throw new DBError("Database `"+name+"` does not exist");
    }

    void assertNotExists(String name) throws DBError{
        if(indexOf(name)>=0)throw new DBError("Database `"+name+"` already exists");
    }

    //returns index of table or -1 if no error with this name
    int indexOf(String name){
        for(int i=0;i<this.size();i++){
            if(this.get(i).name.equals(name))return i;
        }
        return -1;
    }
}
