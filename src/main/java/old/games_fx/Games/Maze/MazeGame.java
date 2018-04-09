package old.games_fx.Games.Maze;

import old.games_fx.KeybordListener.ArrowDirection;
import old.games_fx.Sounds.Sounds;

/**
 * the logic/rules of the game without calculation
 *
 * @author Elias
 */
public class MazeGame extends AbstractGameSubject {

    private final Sounds sound;//calls Object
    private boolean[][] gameField;//varaibles
    private final int playerPosition[] = new int[2];
    private int length, height;

    /**
     * creates an new MazeGame Object
     */
    public MazeGame() {
        sound = new Sounds();
        reset();
    }

    /**
     * tries to move the player in the direction
     *
     * @param nextMove
     */
    public void move(ArrowDirection nextMove) {
        int xKord = playerPosition[0];
        int yKord = playerPosition[1];
        switch (nextMove) {
            case Up: {
                yKord--;
                break;
            }
            case Down: {
                yKord++;
                break;
            }
            case Right: {
                xKord++;
                break;
            }
            case Left: {
                xKord--;
                break;
            }
        }
        if (xKord >= 0 && yKord >= 0 && xKord < height && yKord < length) {
            if (gameField[yKord][xKord]) {
                playerPosition[0] = xKord;
                playerPosition[1] = yKord;
                notifyObserversOfPositionChange();
            } else {//if the player hits an wall
                sound.lose();
            }
        } else {//if the player is at the edge of the game field
            sound.lose();
        }
    }

    /**
     * enum with all four possible Direction of the game (Up, Down, right and
     * left)
     */
    public enum Direction {

        /**
         *
         */
        UP,

        /**
         *
         */
        DOWN,

        /**
         *
         */
        RIGHT,

        /**
         *
         */
        LEFT
    }

    /**
     * creates an new Maze an sets the Playerposition to default
     *
     * @param length
     * @param height
     */
    public void newGame(int length, int height) {
        reset();
        MazeCalculation berechnung = new MazeCalculation();
        this.length = length;
        this.height = height;
        gameField = berechnung.newMaze(length, height);
    }

    /**
     * @return gives back if the player has already reached the goal
     */
    public boolean getEnde() {
        return playerPosition[0] == height - 1 && playerPosition[1] == length - 1;
    }

    /**
     * resets the position of the player to default
     */
    public void reset() {
        playerPosition[0] = 0;
        playerPosition[1] = 0;
    }

    /**
     * @return gets the actual position of the player inn the maze
     */
    public int[] getPlayerPosition() {
        return playerPosition;
    }

    /**
     * @return gets the actual Maze
     */
    public boolean[][] getMaze() {
        return gameField;
    }
}
