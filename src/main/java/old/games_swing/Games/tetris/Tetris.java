package old.games_swing.Games.tetris;

import old.games_swing.GameLogic.ArrayCopy;
import old.games_swing.GameLogic.ScoreCounter;
import old.games_swing.GameLogic.TimeCalculation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author Elias
 */
public class Tetris extends AbstractGameSubject {

    private final boolean gamefield[][];
    private boolean nextForm[][], currentForm[][];
    private boolean GameOver;
    private final Timer gameTimer;
    private int length, height;
    private final TimeCalculation time;
    private final ScoreCounter score;

    /**
     * <p>
     * an new Tetris game is created.</p>
     * <p>
     * needs to be given the length and the height oft he gamefield</p>
     *
     * @param length the length of the gamefield
     * @param height the height of the gamefield
     */
    public Tetris(int length, int height) {
        gamefield = new boolean[length][height];

        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //move down
            }
        });
        this.score = new ScoreCounter("Punkte: ", 0);
        this.time = new TimeCalculation();
        Reset();
    }

    /**
     * enum of the actuel type
     */
    public enum TetrisFormen {

        LForm1, IForm, OForm, TForm
    }

    /**
     * @return gives back an boolean,that stands for if the game is over
     */
    public boolean isOver() {
        return GameOver;
    }

    /**
     * @return JLabel with chancing score
     */
    public JLabel getScoreLabel() {
        return score.getchangingLabel();
    }

    public boolean[][] getNextForm() {
        return nextForm;
    }
    

    /**
     * @return JLabel with chancing time
     */
    public JLabel getTimeLabel() {
        return time.getChanginglabel();
    }

    /**
     * starts the game
     */
    public void startGame() {
        gameTimer.start();
    }

    /**
     * pauses the game
     */
    public void pauseGame() {
        gameTimer.stop();
    }

    /**
     * stops and resets the game
     */
    public void Reset() {
        gameTimer.stop();
        for (int i = 0; i < length; i++) {
            for (int index = 0; index < height; index++) {
                gamefield[i][index] = false;
            }
        }
        time.reset();
        score.setScore(1, 0);
    }

    private void newForm() {
        currentForm=ArrayCopy.clone2DArrayBoolean(nextForm);
        int a= (int)Math.random()*4;
        switch (a){
            case 0:
                
                break;
            case 1: break;
                    case 2 :break;
                    case 3 :break;
        }
    }

    /**
     * tries to rotate the falling Object if not posible gives back false;
     *
     * @param upLeftCorner
     * @param downRightCorner
     * @return boolean state of method
     */
    public boolean rotateObject(int[] upLeftCorner, int[] downRightCorner) {
        boolean possible = true;
        for (int index = upLeftCorner[0]; index < downRightCorner[0]; index++) {
            if (gamefield[downRightCorner[0] + 1][downRightCorner[1]]) {
                possible = false;
                break;
            }
        }
        if (possible) {

        }
        if (possible) {
            //rotate object
            return true;
        } else {
            return false;
        }
    }

    private boolean canBeMovedDown(int[] upLeftCorner, int[] downRightCorner) {
        boolean possible = true;
        for (int index = upLeftCorner[0]; index < downRightCorner[0]; index++) {
            if (gamefield[downRightCorner[0] + 1][downRightCorner[1]]) {
                possible = false;
                break;
            }
        }
        if (possible) {
            moveObjectDown(upLeftCorner, downRightCorner);
            return true;
        } else {
            return false;
        }
    }

    private void moveObjectDown(int[] upLeftCorner, int[] downRightCorner) {//moves the Object down

    }

    private void hasRow() {
        for (int i = 0; i < height; i++) {
            boolean row = true;
            for (int index = 0; index < length; index++) {
                if (!gamefield[i][index]) {
                    row = false;
                    break;
                }
            }
            if (row) {
                moveDeleteRow(i);
            }
        }
    }

    private void moveDeleteRow(int importIndex) {
        ArrayList<int[]> cordinates = new ArrayList<>();
        for (int index = 0; index < length; index++) {
            gamefield[importIndex][index] = false;
            int[] a = {importIndex, index};
            cordinates.add(a.clone());
        }
        notifyObservers(cordinates, null);
        //verschieben nach unten
        ArrayList<int[]> cord = new ArrayList<>();
        ArrayList<int[]> cord2 = new ArrayList<>();
        for (int i = importIndex + 1; i < height; i++) {
            System.arraycopy(gamefield[i], 0, gamefield[i - 1], 0, length);
        }
        notifyObservers(cord, cord2);
    }
}
