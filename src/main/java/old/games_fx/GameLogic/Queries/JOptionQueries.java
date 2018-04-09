package old.games_fx.GameLogic.Queries;

import old.games_fx.Icons.Icons;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 * a collection of askingOptions that need no initialisation
 *
 * @author Elias Schorr, BBBaden
 * @since 16.09.2014
 * @version 1.0
 */
public class JOptionQueries implements Queries {

    @Override
    public options askYesNo(String question, String title) {
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

    @Override
    public void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE, Icons.getBF3_Icon());
    }

    @Override
    public int[] askNewSizes(int min, int max, String titel) {
        int a[] = new int[2];
        a[0] = askSize("heigth", min, max, titel);
        a[1] = askSize("length", min, max, titel);
        if (a[0] == -1 || a[1] == -1) {
            a[0] = -1;
            a[1] = -1;
        }
        return a;
    }

    @Override
    public int askSize(String sizeName, int min, int max, String titel) {
        int size = -1;
        while (true) {
            try {
                String eingabe = JOptionPane.showInputDialog(null,
                        "how large (" + sizeName + ") ? (min. " + min + " max. " + max + ")", titel, JOptionPane.QUESTION_MESSAGE);
                size = Integer.parseInt(eingabe);
                if (size >= min && size <= max) {
                    break;
                } else if (size == JOptionPane.CLOSED_OPTION || size == JOptionPane.CANCEL_OPTION) {
                    size = -1;
                } else {
                    JOptionPane.showMessageDialog(null, "please make an correct input." + min + " und " + max + " ein", titel, JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (HeadlessException | NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "please make an correct input.", titel, JOptionPane.WARNING_MESSAGE);
            }
        }
        return size;
    }

    @Override
    public String askSomething(String question, String titel) {
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
