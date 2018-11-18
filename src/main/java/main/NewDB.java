package main;

import main.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewDB extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textBox;
    static String text=null;

    public NewDB(JFrame root, String name) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        if(name!=null)textBox.setText(name);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text = textBox.getText();
                root.setVisible(true);
                dispose();
            }
        });
    }


    public static String run(JFrame root, String name){
        NewDB dialog = new NewDB(root, name);
        dialog.pack();
        dialog.setVisible(true);
        return text;
    }
}
