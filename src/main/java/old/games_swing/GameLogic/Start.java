package old.games_swing.GameLogic;

import old.games_swing.Sounds.Sounds;
import javax.swing.JOptionPane;

/**
 * old.maze_fx.start class of the old.games_swing.old.games_fx.Games
 *
 * @author Elias
 */
public class Start {

    /**
     * starts the programm and asks the user if he wants background sound or not
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int eingabe = JOptionPane.showConfirmDialog(null, "Wollen sie den Hintergrundsound aktivieren? \n(Einstellung nicht änderbar bis zum nächsten Start)", "Start", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (eingabe == JOptionPane.YES_OPTION) {//activates the sound if the user wants it
            Sounds s1 = new Sounds();
            s1.backgroundMusic();
        }
        GamesAuswahl frame1 = new GamesAuswahl();//opens the game selection frame
        frame1.setVisible(true);//sets it visible
    }
}
