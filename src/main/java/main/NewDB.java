package main;

import main.Database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewDB extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField text;

    public NewDB(JFrame root, Database d) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        if(d!=null)text.setText(d.name);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.name = text.getText();
                root.setVisible(true);
                dispose();
            }
        });
    }


    public static void run(JFrame root, Database d){
        NewDB dialog = new NewDB(root, d);
        dialog.pack();
        dialog.setVisible(true);
    }
}
