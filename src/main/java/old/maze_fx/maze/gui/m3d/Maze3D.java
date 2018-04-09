package old.maze_fx.maze.gui.m3d;

import old.maze_fx.def.keybordListener.ArrowDirection;
import old.maze_fx.maze.models.Field;
import old.maze_fx.maze.models.FieldTyp;
import old.maze_fx.maze.observer.Observer;
import javafx.scene.layout.Pane;

/**
 * this class is for the grapphical concept of a 3D-Maze
 *
 * @author Elias Schorr
 * @since 20.02.2015
 * @version 1.0
 */
public class Maze3D implements Observer {

    private final Draw3DPane bg;
    private ArrowDirection dir;

    /**
     * init
     */
    public Maze3D() {
        this.dir = ArrowDirection.Down;
        bg = new Draw3DPane();
    }

    @Override
    public void onPlayerPositionChange(Field player, Field[] surroundings) {
        boolean b[] = new boolean[4];
        int x = player.getX();
        int y = player.getY();
        switch (dir) {
            case Down: {
                bg.setSurroundings(Position3D.RIGHT, getTyp(x - 1, y, surroundings));
                bg.setSurroundings(Position3D.LEFT, getTyp(x + 1, y, surroundings));
                bg.setSurroundings(Position3D.BACK, getTyp(x, y + 1, surroundings));
                break;
            }
            case Up: {
                bg.setSurroundings(Position3D.RIGHT, getTyp(x + 1, y, surroundings));
                bg.setSurroundings(Position3D.LEFT, getTyp(x - 1, y, surroundings));
                bg.setSurroundings(Position3D.BACK, getTyp(x, y - 1, surroundings));
                break;
            }
            case Right: {
                bg.setSurroundings(Position3D.RIGHT, getTyp(x, y + 1, surroundings));
                bg.setSurroundings(Position3D.LEFT, getTyp(x, y + 1, surroundings));
                bg.setSurroundings(Position3D.BACK, getTyp(x + 1, y, surroundings));
                break;
            }
            case Left: {
                bg.setSurroundings(Position3D.RIGHT, getTyp(x, y - 1, surroundings));
                bg.setSurroundings(Position3D.LEFT, getTyp(x, y + 1, surroundings));
                bg.setSurroundings(Position3D.BACK, getTyp(x - 1, y, surroundings));
                break;
            }
        }
    }

    private FieldTyp getTyp(int x, int y, Field[] fs) {
        for (Field f : fs) {
            if (f.getX() == x && f.getY() == y) {
                return f.getFieldTyp();
            }
        }
        return FieldTyp.WALL;
    }

    @Override
    public void newGame(int length, int heigth) {
        //nothing
    }

    /**
     * gives back the 3D-Pane
     *
     * @return the pane
     */
    public Pane getPane() {
        return bg;
    }

    @Override
    public void onDirectionChange(ArrowDirection direction) {
        this.dir = direction;
    }
}
