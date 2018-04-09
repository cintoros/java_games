package old.games_swing.Games.Maze.Maze_2D;

import java.awt.Color;

/**
 * a Wall that you can't go past
 *
 * @author Elias
 */
public class Wall extends MazeObject {

    /**
     * creates an new Wall Object
     */
    public Wall() {
        setBackground(Color.black);
        setVisible(true);
    }
}
