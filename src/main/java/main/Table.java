package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Table {
    String name;
    Columns columns;

    public synchronized Table columnAdd(String name, String typeName) throws DBError {
        assertNotExists(name);
        this.columns.put(name, new Column(typeName));
        return this;
    }
    public synchronized Table valuesAdd(List<Object> row) throws DBError {
        if(row==null)throw new DBError("row can't be null");
        if(row.size()!=columns.size())throw new DBError("number of values "+row.size()+" does not correspond to number of columns "+columns.size()+" in table "+name);
        //validate all values
        int i=0;
        List<Object> valid = new ArrayList<>(row.size());
        for(Map.Entry<String,Column> col:columns.entrySet()){
            valid.add(i, col.getValue().type.cast(row.get(i)));
            i++;
        }
        //insert valid values
        //validation and real insert are separated to be sure that all values inserted or not
        i=0;
        for(Map.Entry<String,Column> col:columns.entrySet()){
            col.getValue().data.add( valid.get(i) );
            i++;
        }
        return this;
    }
    public synchronized Table valuesAdd(Object ... vals) throws DBError {
        List<Object> arr = new ArrayList<>(vals.length);
        for (Object val : vals) {
            arr.add(val);
        }
        return valuesAdd(arr);
    }





    void assertNotExists(String name) throws DBError {
        if(columns.containsKey(name))throw new DBError("Column `"+name+"` already exists in table `"+this.name+"`");
    }
    void assertExists(String name) throws DBError {
        if(!columns.containsKey(name))throw new DBError("Column `"+name+"` does not exist in table `"+this.name+"`");
    }
}
