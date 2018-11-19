package main;

import javax.swing.*;
import java.util.List;

public class GenericListModel<K> extends AbstractListModel<K> {
    private final List<K> list;
    public GenericListModel(List<K> list){
        this.list=list;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public K getElementAt(int index) {
        return list.get(index);
    }

    //generic method to update list container
    void fireChange(){ this.fireContentsChanged(this, 0, list.size()); }
}
