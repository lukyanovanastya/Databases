package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import static main.DatabaseMap.getInstance;

public class Main {
    private JList list;
    private JButton create;
    private JButton delete;
    private JPanel panel;
    private JButton edit;
    private JButton read;
    private JButton save;
    private JScrollPane scrollPane;
    private DatabaseMap bases;
    private DefaultListModel<String> dlm = new DefaultListModel<>();

    public Main() {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setModel(dlm);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        bases = new DatabaseMap();

        String[] test = { "Chrome", "Firefox", "Internet Explorer", "Safari",
                "Opera", "Morrowind", "Oblivion", "NFS", "Half Life 2",
                "Hitman", "Morrowind", "Oblivion", "NFS", "Half Life 2"
        };

        for (String s: test)
        {
            addBase(s, null);
        }


        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                String name = NewDB.run(frame, null);
                if(name!=null) addBase(name, null);
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String)list.getSelectedValue();
                frame.setVisible(false);
                String newName = NewDB.run(frame, name);
                DatabaseNew db = bases.get(name);
                deleteBase(name);
                addBase(newName, db);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String)list.getSelectedValue();
                deleteBase(name);
            }
        });
    }

    public void addBase(String name, DatabaseNew db){
        bases.put(name, db);
        dlm.addElement(name);
    }

    public void deleteBase(String name){
        bases.remove(name);
        dlm.removeElement(name);
    }

    public static void main(String[] args) {
        Main app = new Main();
    }
}
