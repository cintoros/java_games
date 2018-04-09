package old.games_swing.GameLogic.queries;

import old.games_swing.Icons.Icons;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 * a collection of askingOptions that need no initialisation
 *
 * @author Elias Schorr, BBBaden
 * @since 16.09.2014
 * @version 1.0
 */
public class Queries {

    /**
     * Options to choose
     */
    public enum options {

        /**
         * approved
         */
        YES,
        /**
         * not approved
         */
        NO,
        /**
         * cancelled
         */
        CANCEL
    }

    public static options askYesNo(String question, String title) {
        int result = JOptionPane.showConfirmDialog(null, question, title, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, Icons.getBF3_Icon());
        System.out.println(result);
        options option = options.CANCEL;
        switch (result) {
            case 2: {
                option = options.CANCEL;
                break;
            }
            case 1: {
                option = options.NO;
                break;
            }
            case 0: {
                option = options.YES;
                break;
            }
        }
        return option;
    }

    public static void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE, Icons.getBF3_Icon());
    }

    /**
     * gives back the heigth in the index 0 and the length in the index 1. If
     * the user has aborted the Gui it returns -1 in the array
     *
     * @param min the minimal size
     * @param max the maximal size
     * @param titel the titel of the Programm
     * @return gives back the heigt and length
     */
    public static int[] askNewSizes(int min, int max, String titel) {
        int a[] = new int[2];
        a[0] = askSize("Höhe", min, max, titel);
        a[1] = askSize("Länge", min, max, titel);
        if (a[0] == -1 || a[1] == -1) {
            a[0] = -1;
            a[1] = -1;
        }
        return a;
    }

    /**
     * gives back the size between(including) the min and max or -1 if it is
     * canceled. The User is asked over an JOptionPane with the titel of the
     * program
     *
     * @param sizeName the name of the Size
     * @param min the minimal size
     * @param max the maximal size
     * @param titel the titel of the Programm
     * @return gives back the size
     */
    public static int askSize(String sizeName, int min, int max, String titel) {
        int size = -1;
        while (true) {
            try {
                String eingabe = JOptionPane.showInputDialog(null,
                        "Wie gross soll es (" + sizeName + ") sein (min. " + min + " max. " + max + ")", titel, JOptionPane.QUESTION_MESSAGE);
                size = Integer.parseInt(eingabe);
                if (size >= 10 && size <= 100) {
                    break;
                } else if (size == JOptionPane.CLOSED_OPTION || size == JOptionPane.CANCEL_OPTION) {
                    size = -1;
                } else {
                    JOptionPane.showMessageDialog(null, "Bitte geben Sie eine Zahl zwischen " + min + " und " + max + " ein", titel, JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Bitte tätigen Sie eine gültige Eingabe", titel, JOptionPane.WARNING_MESSAGE);
            }
        }
        return size;
    }

    /**
     * asks the user the question and returns the answer as String
     *
     * @param question the message
     * @param titel the titel of the Programm
     * @return gives back the answer
     */
    public static String askSomething(String question, String titel) {
        String txt = "";
        while (true) {
            String eingabe = JOptionPane.showInputDialog(null, question, titel, JOptionPane.QUESTION_MESSAGE);
            if ("".equals(txt)) {
                showMessage("please make an correct input", titel);
            } else {
                break;
            }
        }
        return txt;
    }
}
