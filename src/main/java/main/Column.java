package main;

import java.util.ArrayList;
import java.util.Arrays;

public class Column {
    Types type;
    Data data;

    public Column(String typeName){
        this.type = Types.valueOf(typeName);
        this.data = new Data();
    }

    public String toString(){
        return "{type="+type+", data="+data.toString()+"}";
    }
}
