package main;

import javax.swing.*;

public class DatabaseMapModel extends AbstractListModel<Database> {
    private final DatabaseMap delegate;
    public DatabaseMapModel(DatabaseMap db){
        this.delegate=db;
    }

    @Override
    public int getSize() {
        return delegate.size();
    }

    @Override
    public Database getElementAt(int index) {
        return delegate.get(index);
    }
}
