package old.maze_fx.maze.controller;

import java.util.ArrayList;
import old.maze_fx.Sounds.Sounds;
import old.maze_fx.def.keybordListener.ArrowDirection;
import old.maze_fx.def.keybordListener.KeyObserver;
import old.maze_fx.maze.models.Field;
import old.maze_fx.maze.models.FieldTyp;
import old.maze_fx.maze.models.Maze;
import old.maze_fx.maze.observer.AbstractSubject;
import shared.JOptionQueries;

/**
 * the logic/rules of the game without calculation
 *
 * @author Elias
 */
public class MazeGame extends AbstractSubject implements KeyObserver, Runnable {

    private final Sounds sound;//calls Object
    private final Maze m = new Maze();
    private final Field player;
    private ArrowDirection direction;
    private boolean mode3d;

    public boolean isMode3d() {
        return mode3d;
    }

    /**
     * creates an new MazeGame Object
     */
    public MazeGame() {
        this.direction = ArrowDirection.Down;
        this.player = new Field(0, 0, FieldTyp.PLAYER);
        sound = new Sounds();
        reset();
    }

    /**
     * tries to move the player in a direction or to change the direction he's
     * locking to
     *
     * @param nextMove the direction to move
     */
    public void move(ArrowDirection nextMove) {
        if (mode3d) {//if it's 3D then he can change the direction he's looking to
            switch (nextMove) {
                case Up: {
                    moveIt(direction);
                    break;
                }
                case Down: {
                    toDir(direction.ordinal() + 2);
                    break;
                }
                case Right: {
                    toDir(direction.ordinal() + 1);
                    break;
                }
                case Left: {
                    toDir(direction.ordinal() + 3);
                    break;
                }
            }
            notifyDirection(direction);
        } else {//else he just ties to move
            moveIt(nextMove);
            notifyDirection(nextMove);
        }//when all changes are done the playfield is updated.
        Field[] surroundings = getSurroundings(player);
        notifyChange(player, surroundings);
    }

    /**
     * saves the direction that is represented by the number i
     *
     * @param i the number
     */
    private void toDir(int i) {
        if (i > 3) {
            i = i - 4;
        }
        switch (i) {
            case 0: {
                direction = ArrowDirection.Up;
                break;
            }
            case 1: {
                direction = ArrowDirection.Right;
                break;
            }
            case 2: {
                direction = ArrowDirection.Down;
                break;
            }
            case 3: {
                direction = ArrowDirection.Left;
                break;
            }
        }
    }

    /**
     * tries to move the player in the direction given
     *
     * @param nextMove the direction
     */
    private void moveIt(ArrowDirection nextMove) {
        int x = player.getX();
        int y = player.getY();
        switch (nextMove) {
            case Up: {
                y--;
                break;
            }
            case Down: {
                y++;
                break;
            }
            case Right: {
                x++;
                break;
            }
            case Left: {
                x--;
                break;
            }
        }
        if (x >= 0 && y >= 0 && x < m.getLength() && y < m.getHeigth()) {
            Field f = m.getField(x, y);
            if (f.isFieldTyp(FieldTyp.WAY) || f.isFieldTyp(FieldTyp.START)) {
                player.setPosition(x, y);
            } else if (f.isFieldTyp(FieldTyp.GOAL)) {//if the player reches the goals
                player.setPosition(x, y);
                sound.win();
                JOptionQueries.showMessage("Game Over", "Game Over");
            } else {// if the player hits an wall
                sound.lose();
            }
        } else {//if the player is at the edge of the game field
            sound.lose();
        }
    }

    @Override
    public void onStateChange(ArrowDirection direction) {
        move(direction);
    }

    /**
     * creates an new Maze an sets the Playerposition to default
     */
    public void newGame() {
        int a[] = JOptionQueries.askNewSizes(10, 100, "Minesweeper");
        m.newMaze(a[1], a[0]);
        notifyNewGame(a[1], a[0]);
        reset();
    }

    /**
     * @return gives back if the player has already reached the goal
     */
    public boolean getEnde() {
        return player.getX() == m.getHeigth() - 1 && player.getY() == m.getLength() - 1;
    }

    /**
     * resets the position of the player to default
     */
    private void reset() {
        this.player.setPosition(0, 0);
        Field[] surroundings = getSurroundings(player);
        notifyChange(player, surroundings);

    }

    /**
     * gives back the surrounding fields of an field f
     *
     * @param f the field in the middle
     * @return the surroundings
     */
    private Field[] getSurroundings(Field f) {
        ArrayList<Field> array = new ArrayList<>();
        int x = f.getX();
        int y = f.getY();
        tryAdd(array, x + 1, y + 1);
        tryAdd(array, x + 1, y);
        tryAdd(array, x + 1, y - 1);
        tryAdd(array, x, y + 1);
        tryAdd(array, x, y - 1);
        tryAdd(array, x - 1, y + 1);
        tryAdd(array, x - 1, y);
        tryAdd(array, x - 1, y - 1);
        return toArray(array);
    }

    /**
     * makes an array out of an ArrayList
     *
     * @param array the ArrayList
     * @return the array
     */
    private Field[] toArray(ArrayList<Field> array) {
        Field f[] = new Field[array.size()];
        for (int i = 0; i < array.size(); i++) {
            f[i] = array.get(i)
                    .clone();
        }
        return f;
    }

    /**
     * tries to add the specific field to the array
     *
     * @param array the array
     * @param x     the x corrdinate of the field
     * @param y     the y corrdinate of the field
     */
    private void tryAdd(ArrayList<Field> array, int x, int y) {
        Field f = m.getField(x, y);
        if (f != null) {
            array.add(f);
        } else {
        }
    }

    /**
     * switches the mode from 2D to 3D and back
     */
    public void switchMode() {
        mode3d = !mode3d;
    }

    @Override
    public void run() {
        System.out.println("run");
    }
}
