package old.maze_fx.maze.models;

/**
 * @author Elias Schorr
 * @since 21.01.2015
 * @version 1.0
 */
public class Field {

    private int x, y;
    private FieldTyp fieldTyp;

    public Field() {
    }

    public Field(int x, int y, FieldTyp fieldTyp) {
        this.x = x;
        this.y = y;
        this.fieldTyp = fieldTyp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public FieldTyp getFieldTyp() {
        return fieldTyp;
    }

    public void setFieldTyp(FieldTyp fieldTyp) {
        this.fieldTyp = fieldTyp;
    }

    @Override
    public Field clone() {
        return new Field(x, y, fieldTyp);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isFieldTyp(FieldTyp fieldTyp) {
        return this.fieldTyp == fieldTyp;
    }

}
