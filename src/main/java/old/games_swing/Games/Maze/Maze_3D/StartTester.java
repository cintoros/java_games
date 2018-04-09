package old.games_swing.Games.Maze.Maze_3D;

import old.games_swing.GameLogic.DefFrame;

/**
 * @author Elias
 * @school BBBaden
 * @created 03.09.2014
 * @version 1.0
 */
public class StartTester {

    public static void main(String[] args) {
        DefFrame frame = new DefFrame(600, 600, "hi");
        boolean surroundings[][] = new boolean[3][3];
        //andere
        DrawPanel panel = new DrawPanel(surroundings);
        frame.add(panel);
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        surroundings[1][1] = true;
        panel.setSurroundings(surroundings);
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        surroundings[1][2] = true;//right
        surroundings[1][0] = true;//left
        panel.setSurroundings(surroundings);
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
        surroundings[0][0] = true;
        surroundings[0][1] = true;
        surroundings[0][2] = true;
        surroundings[2][0] = true;
        surroundings[2][1] = true;//ahead
        surroundings[2][2] = true;
        panel.setSurroundings(surroundings);
    }

}
