package main;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class Database {
    String name;
    HashMap<String,HashMap<String, HashMap<String, ArrayList>>> data;

    public Database(){}

    public Database(String name){
        this.name = name;
        data = new HashMap<>();
    }

    public void read(String file) throws Exception{
        Gson gson =  new Gson();
        HashMap<String, HashMap> json = new HashMap<>();
        json = gson.fromJson(new FileReader(file), json.getClass());
        for(String s:json.keySet()){
            name=s;
        }
        data = json.get(name);
    }

    public void write(Database db, String file) throws IOException {
        HashMap<String, HashMap> json = new HashMap<>();
        json.put(name, data);
        Gson gson = new Gson();
        gson.toJson(json, new FileWriter(file));
    }

    public ArrayList<String> getTables(){
        ArrayList<String> tables = new ArrayList<>();
        for (String key:data.keySet()) {
            tables.add(key);
        }
        return tables;
    }

    public ArrayList<String> getAtributes(String table){
        ArrayList<String> atrs = new ArrayList<>();
        for (String key:data.get(table).keySet()){
            atrs.add(key);
        }
        return atrs;
    }

    public Types getType(String table, String atribute){
        String str=null;
        for(String s:data.get(table).get(atribute).keySet()){
            str=s;
        }
        Types type = null;
        for(Types t:Types.values()){
            if(t.getString()==str) type=t;
        }
        return type;
    }

    public String toString(){
        return this.name;
    }
}
