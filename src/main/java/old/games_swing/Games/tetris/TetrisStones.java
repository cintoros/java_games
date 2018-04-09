package old.games_swing.Games.tetris;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author Elias
 */
public class TetrisStones extends JPanel {

    private State status;

    /**
     * creates an new TetrisStone and cleanes it
     */
    public TetrisStones() {
        super();
        clean();
    }

    /**
     * an enum of diffrent States for the Stone
     */
    public enum State {

        /**
         * the Stone is empty
         */
        empty,
        /**
         * the Stone is filled
         */
        filled
    }

    /**
     * sets the State to filled
     */
    public void fill() {
        status = State.filled;
        this.setBackground(Color.white);
    }

    /**
     * sets the State to empty
     */
    public void clean() {
        status = State.empty;
        this.setBackground(Color.black);
    }
}
