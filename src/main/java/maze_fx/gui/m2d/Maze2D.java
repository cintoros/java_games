package maze_fx.gui.m2d;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import maze_fx.models.Field;
import maze_fx.observer.Observer;
import shared.keybordListener.Direction;

public class Maze2D implements Observer {

    private MazeField fields[][];
    private final GridPane bg_2;

    public Maze2D() {
        bg_2 = new GridPane();
    }

    @Override
    public void onPlayerPositionChange(Field player, Field[] surroundings) {
        fields[player.getX()][player.getY()].setFieldTyp(player.getFieldType());
        for (Field f : surroundings) {
            fields[f.getX()][f.getY()].setFieldTyp(f.getFieldType());
        }
    }

    @Override
    public void newGame(int length, int heigth) {
        bg_2.getChildren()
                .clear();
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
    public void onDirectionChange(Direction direction) {
        //nothing
    }
}
