package old.games_fx.GameLogic;

import old.games_fx.DefaultGrapics.ChangingLabel.AbstractChangeSubject;

/**
 * this class is for counting points of 1 to X players and giving it back on an
 * changing JLabel or on request
 *
 * @author Elias Schorr, BBBaden
 * @since 05.10.2014
 * @version 1.0
 */
public class ScoreCounter extends AbstractChangeSubject {

    private final int scores[];//array to save the score of the players
    private final String messages[];//the static Message that is given out before the score

    /**
     * creates an new ScoreCounterObject with the given number of scores. All
     * new scores will have the score 0 and the message "score: "
     *
     * @param numberOfScores how many diffrent scores there are
     */
    public ScoreCounter(int numberOfScores) {
        messages = new String[numberOfScores];
        scores = new int[numberOfScores];
        for (int i = 0; i < numberOfScores; i++) {
            messages[i] = "score: ";
            scores[i] = 0;
        }
    }

    /**
     * the given player becomes the given points
     *
     * @param player
     * @param number
     */
    public void addPoints(int player, int number) {
        try {
            scores[player - 1] += number;
            updateScore();
        } catch (Exception e) {
            showError();
        }
    }

    /**
     * the given player loses the given points
     *
     * @param player
     * @param number
     */
    public void removePoints(int player, int number) {
        try {
            scores[player - 1] -= number;
            updateScore();
        } catch (Exception e) {
            showError();
        }
    }

    /**
     * the given player becomes the given score
     *
     * @param player
     * @param number
     */
    public void setScore(int player, int number) {
        try {
            scores[player - 1] = number;
            updateScore();
        } catch (Exception e) {
            showError();
        }
    }

    /**
     *
     * @param player
     * @param message
     */
    public void setMessage(int player, String message) {
        try {
            messages[player - 1] = message;
            updateScore();
        } catch (Exception e) {
            showError();
        }
    }

    /**
     * updates the score
     */
    public void updateScore() {
        String message = "";
        for (int i = 0; i < messages.length; i++) {
            message += messages[i];
            message += scores[i] + " / ";
        }
        setText(message);
    }

    /**
     * @param score
     * @return returns the points of the given player
     */
    public int getPointsof(int score) {
        try {
            return scores[score - 1];
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * sets the text of the observers
     */
    private void setText(String message) {
        this.notifyObservers(message);
    }

    private void showError() {
        System.out.println("player is not valid!");
    }
}
