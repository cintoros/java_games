package old.games_fx.Games.Maze.Maze_2D;

import java.awt.Color;
//import javax.swing.BorderFactory;

/**
 * a way you can get past
 *
 * @author Elias
 */
public class Way extends MazeObject {

    /**
     * creates an new way object
     */
    public Way() {
        setBackground(Color.white);
        setVisible(true);
    }

    /**
     * method called when go over this field activates an hint that you have be
     * there
     */
    @Override
    public void goOver() {
        setBackground(Color.gray);
    }
}
