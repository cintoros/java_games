package shared;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

public class JOptionQueries {

    public static void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
    }


    public static int[] askNewSizes(int min, int max, String titel) {
        int a[] = new int[2];
        a[0] = askSize("heigth", min, max, titel);
        a[1] = askSize("length", min, max, titel);
        if (a[0] == -1 || a[1] == -1) {
            a[0] = -1;
            a[1] = -1;
        }
        return a;
    }

    private static int askSize(String sizeName, int min, int max, String titel) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, "how large (" + sizeName + ") ? (min. " + min + " max. " + max + ")", titel, JOptionPane.QUESTION_MESSAGE);
                int size = Integer.parseInt(input);
                if (size >= min && size <= max) {
                    return size;
                } else {
                    JOptionPane.showMessageDialog(null, String.format("please make an correct input. between %s and %s", min, max), titel, JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, String.format("please make an correct input. between %s and %s", min, max), titel, JOptionPane.WARNING_MESSAGE);
            }
        }
    }

}
