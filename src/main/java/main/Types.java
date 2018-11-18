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
    Object cast(String o) throws JsonParseException {
        if(o==null)return null;
        try {
            if(this==CHAR){
                if(o.length()!=1)throw new Exception(this.name()+ " must have exactly one character, but met "+o.length());
                return new Character(o.charAt(0));
            }else {
                Constructor c = clazz.getConstructor(o.getClass());
                return c.newInstance(o);
            }
        }catch (Exception e) {
            throw new JsonParseException("can't cast `"+o+"` to "+clazz+": "+e.getMessage());
        }
    }
}
