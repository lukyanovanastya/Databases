package main;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UITools {
    //shows standard edit dialog to modify one string
    //see: https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html
    public static String prompt(Component parent, String title, String value, String label){
        String s = (String) JOptionPane.showInputDialog(
                parent,
                label+":",
                title,
                JOptionPane.INFORMATION_MESSAGE,
                null, //icon,
                null, //of possible values could be here
                value);
        return s;
    }
    public static void alert(Component parent, String title, String messsage){
        JOptionPane.showMessageDialog(parent, messsage, title,JOptionPane.INFORMATION_MESSAGE);
    }
    public static void error (Component parent, String messsage){
        JOptionPane.showMessageDialog(parent, messsage, "Error",JOptionPane.ERROR_MESSAGE);
    }
    public static void error (Component parent, Throwable messsage){
        error(parent, messsage.toString());
    }
    public static String prompt(Component parent, String title, String value) {
        return prompt(parent, title, value, title);
    }

    public static void fireChange(JList list){
        ((GenericListModel)list.getModel()).fireChange();
    }

    public static File selectFile(Component parent){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("."));
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile;
        }
        return null;
    }

}
