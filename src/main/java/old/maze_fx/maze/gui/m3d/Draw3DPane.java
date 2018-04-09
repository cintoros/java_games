package old.maze_fx.maze.gui.m3d;

import old.maze_fx.maze.models.FieldTyp;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

/**
 * this class is responsible for the design of the 3d maze
 *
 * @author Elias Schorr
 * @since 20.02.2015
 * @version 1.0
 */
public class Draw3DPane extends Pane {

    private final Polygon right, left, back, bottom;
    private final GaussianBlur b;

    /**
     * surroundings are set to false
     */
    public Draw3DPane() {
        this(new boolean[4]);
    }

    /**
     * sets the old.maze_fx.start value of the surroundings and paints them
     *
     * @param surroundings the old.maze_fx.start value of the surroundings
     */
    public Draw3DPane(boolean[] surroundings) {
        Polygon rightC, leftC, celling, rightBo, leftBo, rightBa, leftBa,
                backC, backL, backR, backBo, backBa;
        backL = new Polygon(new double[]{250, 250, 250, 750, 350, 650, 350, 350});
        backC = new Polygon(new double[]{250, 250, 750, 250, 650, 350, 350, 350});
        backR = new Polygon(new double[]{750, 750, 750, 250, 650, 350, 650, 650});
        backBo = new Polygon(new double[]{750, 750, 250, 750, 350, 650, 650, 650});
        backBa = new Polygon(new double[]{650, 650, 650, 350, 350, 350, 350, 650});
        leftBa = new Polygon(new double[]{250, 250, 250, 750, 0, 750, 0, 250});
        rightBa = new Polygon(new double[]{750, 250, 750, 750, 1000, 750, 1000, 250});
        leftC = new Polygon(new double[]{0, 0, 250, 250, 0, 250});
        rightC = new Polygon(new double[]{1000, 0, 750, 250, 1000, 250});
        leftBo = new Polygon(new double[]{0, 1000, 250, 750, 0, 750});
        rightBo = new Polygon(new double[]{1000, 1000, 750, 750, 1000, 750
        });
        celling = new Polygon(new double[]{0, 0, 1000, 0, 750, 250, 250, 250});
        bottom = new Polygon(new double[]{0, 1000, 1000, 1000, 750, 750, 250, 750});
        right = new Polygon(new double[]{1000, 0, 1000, 1000, 750, 750, 750, 250});
        left = new Polygon(new double[]{0, 0, 0, 1000, 250, 750, 250, 250});
        back = new Polygon(new double[]{750, 750, 750, 250, 250, 250, 250, 750});
        b = new GaussianBlur(4);
        setStrokeAll(rightC, leftC, celling, bottom, rightBo, leftBo, rightBa,
                leftBa, backC, backL, backR, backBo, backBa, right, left, back);
        setIdAll("WALL", celling, leftC, rightC, leftBo, rightBo);
        setIdAll("PLAYER", bottom);
        setIdAll("HIDDEN", leftBa, rightBa, backBa,
                backBo, backC, backL, backL, backR);
        getChildren().addAll(leftBa, rightBa, leftC, rightC, leftBo, rightBo, backBa,
                celling, bottom, backL, backC, backR, backBo, right, left, back);
        setEffect(b);
//        setEffectAll(b, leftBa, rightBa, leftC, rightC, leftBo, rightBo, backBa,
//                celling, bottom, backL, backC, backR, backBo, right, left, back);
    }

   
    /**
     * alters the surroundings
     *
     * @param pos the position to be altered
     * @param typ the new typ of the position
     */
    public void setSurroundings(Position3D pos, FieldTyp typ) {
        switch (pos) {
            case RIGHT: {
                right.setId(typ.toString());
                break;
            }
            case LEFT: {
                left.setId(typ.toString());
                break;
            }
            case BACK: {
                back.setId(typ.toString());
                break;
            }
            case BOTTOM: {
                bottom.setId(typ.toString());
                break;
            }
        }

    }

    private void setEffectAll(Effect e, Polygon... ps) {
        for (Polygon p : ps) {
            p.setEffect(e);
        }
    }

    /**
     * sets the Id for all Elements
     *
     * @param id the id
     * @param ps the Elements
     */
    private void setIdAll(String id, Polygon... ps) {
        for (Polygon p : ps) {
            p.setId(id);
        }
    }

    /**
     * sets the Stroke for all Elements
     *
     * @param ps the Elements
     */
    private void setStrokeAll(Polygon... ps) {
        Paint darkgray = Paint.valueOf("darkgray");
        for (Polygon p : ps) {
            p.setStroke(darkgray);
        }
    }

}
