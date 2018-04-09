package old.maze_fx.maze.gui.m2d;

import old.maze_fx.maze.models.FieldTyp;
import static old.maze_fx.maze.models.FieldTyp.HIDDEN;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;

/**
 * class that epresaents a field in the maze
 *
 * @author Elias Schorr
 * @since 21.01.2015
 * @version 1.0
 */
public class MazeField {

    private final Pane pane;
    private final GaussianBlur b;
    private FieldTyp typ;

    /**
     * init the field and hides it
     */
    public MazeField() {
        this.b = new GaussianBlur(2);
        pane = new Pane();
        pane.setPrefSize(20, 20);
        pane.setEffect(b);
        hide();
    }

    private void hide() {
        b.setRadius(10);
        typ = HIDDEN;
        pane.setId(typ.toString());
    }

    public void setFieldTyp(FieldTyp fieldTyp) {
        if (typ!=fieldTyp) {
            if (typ==HIDDEN) {
                b.setRadius(2);
            }
            typ = fieldTyp;
            pane.setId(fieldTyp.toString());
        }
    }

    public Pane getPane() {
        return pane;
    }
}
