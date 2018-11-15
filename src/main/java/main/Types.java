package main;

import javax.security.sasl.RealmCallback;

public enum Types {
    INTEGER("integer"),
    REAL("real"),
    CHAR("char"),
    STRING("string"),
    COMPL_INTEGER("compl_integer"),
    COMPL_REAL("compl_real");
    private String name;
    Types(String name){this.name = name;}
    public String getString(){return this.name;}
}
