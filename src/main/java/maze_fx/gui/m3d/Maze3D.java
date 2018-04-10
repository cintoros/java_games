package maze_fx.gui.m3d;

import javafx.scene.layout.Pane;
import maze_fx.models.Field;
import maze_fx.models.FieldType;
import maze_fx.observer.Observer;
import shared.keybordListener.Direction;

public class Maze3D implements Observer {

    private final Draw3DPane bg;
    private Direction dir;

    public Maze3D() {
        this.dir = Direction.Down;
        bg = new Draw3DPane();
    }

    @Override
    public void onPlayerPositionChange(Field player, Field[] surroundings) {
        boolean b[] = new boolean[4];
        int x = player.getX();
        int y = player.getY();
        switch (dir) {
            case Down: {
                bg.setSurroundings(Position3D.Right, getTyp(x - 1, y, surroundings));
                bg.setSurroundings(Position3D.Left, getTyp(x + 1, y, surroundings));
                bg.setSurroundings(Position3D.Back, getTyp(x, y + 1, surroundings));
                break;
            }
            case Up: {
                bg.setSurroundings(Position3D.Right, getTyp(x + 1, y, surroundings));
                bg.setSurroundings(Position3D.Left, getTyp(x - 1, y, surroundings));
                bg.setSurroundings(Position3D.Back, getTyp(x, y - 1, surroundings));
                break;
            }
            case Right: {
                bg.setSurroundings(Position3D.Right, getTyp(x, y + 1, surroundings));
                bg.setSurroundings(Position3D.Left, getTyp(x, y + 1, surroundings));
                bg.setSurroundings(Position3D.Back, getTyp(x + 1, y, surroundings));
                break;
            }
            case Left: {
                bg.setSurroundings(Position3D.Right, getTyp(x, y - 1, surroundings));
                bg.setSurroundings(Position3D.Left, getTyp(x, y + 1, surroundings));
                bg.setSurroundings(Position3D.Back, getTyp(x - 1, y, surroundings));
                break;
            }
        }
    }

    private FieldType getTyp(int x, int y, Field[] fs) {
        for (Field f : fs) {
            if (f.getX() == x && f.getY() == y) {
                return f.getFieldType();
            }
        }
        return FieldType.WALL;
    }

    @Override
    public void newGame(int length, int heigth) {
        //nothing
    }

    public Pane getPane() {
        return bg;
    }

    @Override
    public void onDirectionChange(Direction direction) {
        this.dir = direction;
    }
}
