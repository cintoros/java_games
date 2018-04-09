package old.games_swing.Games.Maze.Maze_3D;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 * @author Elias
 * @school BBBaden
 * @created 05.09.2014
 * @version 1.0
 */
public class SizeAuswahlGUI {

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
                } else if (size == JOptionPane.CLOSED_OPTION || size == JOptionPane.CLOSED_OPTION) {
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
}
