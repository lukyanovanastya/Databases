package main;

import java.util.ArrayList;
import java.util.Arrays;

public class Column {
    Types type;
    Data data;

    public String toString(){
        return "{type="+type+", data="+data.toString()+"}";
    }
}
