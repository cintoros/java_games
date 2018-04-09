package old.games_swing.GameLogic;

import java.awt.Color;
import javax.swing.JLabel;

/**
 * this class is for counting points of 1 to X players and giving it back on an
 * changing JLabel or on request
 *
 * @author Elias
 */
public class ScoreCounter {

    private final JLabel score;//Gui element to give out the actual score
    private final int players[];//array to save the score of the players
    private final String staticMessage;//the static Message that is given out before the score

    /**
     * creates an nee ScoreCounterObject with the given number of players
     *
     * @param numberOfPlayers
     */
    public ScoreCounter(int numberOfPlayers) {
        staticMessage = "Punktestand: 0";
        this.score = new JLabel(staticMessage);
        players = new int[numberOfPlayers];
        updateScore();
    }

    /**
     * creates an new ScoreCounterObject with the given message it can only save
     * one score but can have an other meaning than the score of an player, it
     * needs an old.maze_fx.start score
     *
     * @param message
     * @param start
     */
    public ScoreCounter(String message, int start) {
        this.score = new JLabel(message + start);
        players = new int[1];
        players[0] = start;
        staticMessage = message;
        updateScore();
    }

    /**
     * the given player becomes the given points
     *
     * @param player
     * @param number
     */
    public void addPoints(int player, int number) {
        if (players.length > 1) {
            players[player - 1] += number;
        } else {
            players[0] += number;
        }
        updateScore();
    }

    /**
     * the given player loses the given points
     *
     * @param player
     * @param number
     */
    public void removePoints(int player, int number) {
        if (players.length > 1) {
            players[player - 1] -= number;
        } else {
            players[0] -= number;
        }
        updateScore();
    }

    /**
     * the given player becomes the given score
     *
     * @param player
     * @param number
     */
    public void setScore(int player, int number) {
        if (players.length > 1) {
            players[player - 1] = number;
        } else {
            players[0] = number;
        }
        updateScore();
    }

    private void updateScore() {
        String message = "";
        if (players.length > 1) {
            for (int i = 0; i < players.length; i++) {
                message += " Spieler " + (i + 1) + ": " + players[i];
            }
        } else {
            message = staticMessage + players[0];
        }
        score.setText(message);
    }

    /**
     * @return returns the JLabel with the score
     */
    public JLabel getchangingLabel() {
        return score;
    }

    /**
     * @param player
     * @return returns the points of the given player
     */
    public int getPointsof(int player) {
        if (players.length > 1) {
            return players[player - 1];
        } else {
            return players[0];
        }
    }

    /**
     * this method is to change the fore- and background of the JLabel
     *
     * @param fore
     * @param back
     */
    public void changeLabel(Color fore, Color back) {
        score.setBackground(back);
        score.setForeground(fore);
    }
}
