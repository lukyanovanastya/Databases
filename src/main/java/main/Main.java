package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private DefaultListModel<Database> dlm = new DefaultListModel<>();

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



        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database d = new Database(null);
                frame.setVisible(false);
                NewDB.run(frame, d);
                addBase(d);
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database d = (Database)list.getSelectedValue();
                //dlm.removeElement(d);
                frame.setVisible(false);
                NewDB.run(frame, d);
                //addBase(d);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database d = (Database)list.getSelectedValue();
                deleteBase(d);
            }
        });
    }

    public void addBase(Database db){
        bases.add(db);
        dlm.addElement(db);
    }

    public void deleteBase(Database db){
        bases.remove(db);
        dlm.removeElement(db);
    }

    public static void main(String[] args) {
        Main app = new Main();
    }
}
