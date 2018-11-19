package main;

import com.google.gson.JsonParseException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;

public enum Types {
    //COMPL_INTEGER("compl_integer"),
    //COMPL_REAL("compl_real"),
    REAL(BigDecimal.class),
    CHAR(Character.class),
    INTEGER(BigInteger.class),
    STRING(String.class);


    private Class clazz;

    Types(Class clazz){
        this.clazz = clazz;
    }

    Class clazz(){
        return clazz;
    }

    //converts object (string?) to Types.clazz
    Object cast(Object o) throws DBError {
        if(o==null)return null;
        if(clazz.isAssignableFrom(o.getClass())){
            //value could be assigned to class
            return o;
        }
        //try to convert through string
        try {
            String s=o.toString();
            if(this==CHAR){
                if(s.length()!=1)throw new Exception(this.name()+ " must have exactly one character, but met "+s.length());
                return new Character(s.charAt(0));
            }else {
                Constructor c = clazz.getConstructor(s.getClass());
                return c.newInstance(s);
            }
        }catch (Exception e) {
            throw new DBError("can't cast `"+o+"` of class "+o.getClass()+" to "+clazz+": "+e.getMessage());
        }
    }
}
