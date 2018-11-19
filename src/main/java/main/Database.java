package main;

import java.util.*;

public class Database{
    String name;
    ArrayList<Table> tables;

    public Database(String name){
        this.name=name;
        this.tables = new ArrayList<>();
    }

    @Override
    public String toString(){ return name; }

    @Override
    public boolean equals(Object o){
        if(o instanceof Database)return this.name.equals( ((Database)o).name );
        return false;
    }


    //creates table in the current database
    //

    /**
     * creates table in the current database
     * @param name table name
     * @param cols map where key is column name and value is a column type
     * @return created table
     * @throws DBError if table already exists
     */
    public synchronized Table tableCreate(String name, LinkedHashMap<String,String> cols)throws DBError{
        assertNotExists(name);
        Table t = new Table();
        t.name=name;
        t.columns=new Columns();
        if(cols!=null) {
            for (Map.Entry<String, String> e : cols.entrySet()) {
                t.columns.put(e.getKey(), new Column(e.getValue()));
            }
        }
        this.tables.add(t);
        return t;
    }


    void assertExists(String name) throws DBError{
        if(indexOf(name)==-1)throw new DBError("Table `"+name+"` does not exist in database `"+this.name+"`");
    }

    void assertNotExists(String name) throws DBError{
        if(indexOf(name)>=0)throw new DBError("Table `"+name+"` already exists in database `"+this.name+"`");
    }

    //returns index of table or -1 if no error with this name
    int indexOf(String name){
        for(int i=0;i<tables.size();i++){
            if(tables.get(i).name.equals(name))return i;
        }
        return -1;
    }

}
