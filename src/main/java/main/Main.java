package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    //private DatabaseMap bases;
    //private AbstractListModel<String> dlm = DatabaseMapModel();//new DefaultListModel<>();

    public Main() {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setModel(new DatabaseMapModel(getInstance()));
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        String[] test = {"Chrome", "Firefox", "Internet Explorer", "Safari",
                "Opera", "Morrowind", "Oblivion", "NFS", "Half Life 2",
                "Hitman"
        };

        for (String s : test) {
            getInstance().add(s);
        }


        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                String name = NewDB.run(frame, null);
                if(name!=null) getInstance().add(name);
            }
        });

        /*
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String)list.getSelectedValue();
                frame.setVisible(false);
                String newName = NewDB.run(frame, name);
                Database db = getInstance().remove(name);
                getInstance().put(newName,db);
            }
        });
        */
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("model="+list.getModel());
                    System.out.println("model.size="+list.getModel().getSize());
                    Database db = (Database) list.getSelectedValue();
                    System.out.println("remove db "+db);
                    System.out.println("remove db -> "+getInstance().remove(db));
                } catch (Throwable t) {
                    System.out.println(t.toString());
                }
            }
        });
    }

    /*

    public void addBase(String name, Database db){
        bases.put(name, db);
        dlm.addElement(name);
    }

    public void deleteBase(String name){
        bases.remove(name);
        dlm.removeElement(name);
    }
    */

    public static void main(String[] args) {
        Main app = new Main();
    }

}
