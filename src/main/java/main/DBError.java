package main;

public class DBError extends Exception {
    public DBError(String msg){
        super(msg);
    }
    public DBError(String msg, Throwable cause){
        super(msg, cause);
    }
}
