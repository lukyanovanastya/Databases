package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
    //private AbstractListModel<String> dlm = GenericListModel();//new DefaultListModel<>();

    public Main() {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setModel(new GenericListModel(getInstance()));
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        String[] test = {"Chrome", "Firefox", "Internet Explorer", "Safari",
                "Opera", "Morrowind", "Oblivion", "NFS", "Half Life 2",
                "Hitman"
        };

        for (String s : test) {
            getInstance().add(s);
        }
        UITools.fireChange(list);


        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //frame.setVisible(false);
                String name = UITools.prompt(list, "New DB", null);//NewDB.run(frame, null);
                if(name!=null) getInstance().add(name);
                UITools.fireChange(list);
            }
        });

        read.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = UITools.selectFile(list);
                if(file!=null){
                    try {
                        DatabaseMap.read(file, getInstance());
                        UITools.fireChange(list);
                    } catch (IOException e1) {
                        UITools.error(list, e1);
                    }
                }
                else {
                    UITools.error(list, "File`s not selected");
                }
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Database db = (Database) list.getSelectedValue();
                db.name = UITools.prompt(list, "Edit DB", db.name);
                UITools.fireChange(list);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Database db = (Database) list.getSelectedValue();
                    getInstance().remove(db);
                    UITools.fireChange(list);
                } catch (Throwable t) {
                    System.out.println(t.toString());
                }
            }
        });
    }


    public static void main(String[] args) {
        Main app = new Main();
    }

}
