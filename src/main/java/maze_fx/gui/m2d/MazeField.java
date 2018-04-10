package maze_fx.gui.m2d;

import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import maze_fx.models.FieldType;

public class MazeField {

    private final Pane pane;
    private final GaussianBlur b;
    private FieldType typ;

    /**
     * field is hidden by default
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
        typ = FieldType.HIDDEN;
        pane.setId(typ.toString());
    }

    public void setFieldTyp(FieldType fieldType) {
        if (typ != fieldType) {
            if (typ == FieldType.HIDDEN) {
                b.setRadius(2);
            }
            typ = fieldType;
            pane.setId(fieldType.toString());
        }
    }

    public Pane getPane() {
        return pane;
    }
}
