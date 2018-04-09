package old.games_swing.Games.Maze.Maze_3D;

import old.games_swing.GameLogic.DefFrame;
import old.games_swing.Games.Maze.Maze_2D.MazeObject;
import old.games_swing.Games.Maze.Maze_2D.Wall;
import old.games_swing.Games.Maze.Maze_2D.Way;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * @author Elias
 * @school BBBaden
 * @created 09.09.2014
 * @version 1.0
 */
public class MazeMap {

    DefFrame frame;
    JPanel backGround;
    MazeObject[][] fields;

    public MazeMap(DefFrame frame) {
        this.frame = frame;
    }

    public void show(int height, int lenght, boolean[][] maze) {
        try {
            frame.remove(backGround);
        } catch (Exception ex) {
        }
        fields = new MazeObject[height][lenght];
        backGround = new JPanel(new GridLayout(height, lenght));
        for (int i = 0; i < height; i++) {
            for (int index = 0; index < lenght; index++) {
                if (maze[i][index]) {
                    fields[i][index] = new Way();
                } else {
                    fields[i][index] = new Wall();
                }
                backGround.add(fields[i][index]);
            }
        }
        frame.add(backGround);
        frame.setSize(lenght * 10 + 50, height * 10 + 100);
        fields[height - 1][lenght - 1].setBackground(Color.yellow);
        fields[0][0].setBackground(Color.green);
        frame.add(backGround);
        if (frame.getSize().height == 200) {
            frame.setSize(201, 201);
        } else {
            frame.setSize(200, 200);
        }
    }

    public void visibleChange() {
        if (frame.isVisible()) {
            frame.setVisible(false);
        } else {
            frame.setVisible(true);
        }
    }
}
