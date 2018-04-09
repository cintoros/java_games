package old.maze_fx.maze.gui.m2d;

import old.maze_fx.def.keybordListener.ArrowDirection;
import old.maze_fx.maze.models.Field;
import old.maze_fx.maze.observer.Observer;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @author Elias Schorr
 * @since 21.01.2015
 * @version 1.0
 */
public class Maze2D implements Observer {

    private MazeField fields[][];
    private final GridPane bg_2;
    private Pane bg;

    public Maze2D() {
        bg_2 = new GridPane();
    }

    @Override
    public void onPlayerPositionChange(Field player, Field[] surroundings) {
        fields[player.getX()][player.getY()].setFieldTyp(player.getFieldTyp());
        for (Field f : surroundings) {
            fields[f.getX()][f.getY()].setFieldTyp(f.getFieldTyp());
        }
    }

    @Override
    public void newGame(int length, int heigth) {
        bg_2.getChildren().clear();
        fields = new MazeField[length][heigth];
        for (int x = 0; x < fields.length; x++) {
            for (int y = 0; y < fields[x].length; y++) {
                fields[x][y] = new MazeField();
                bg_2.add(fields[x][y].getPane(), x, y);
            }
        }

        for (MazeField[] fs : fields) {
            for (MazeField f : fs) {
                f = new MazeField();
            }
        }
    }

    public Pane getPane() {
        return bg_2;
    }

    @Override
    public void onDirectionChange(ArrowDirection direction) {
        //nothing
    }
}
