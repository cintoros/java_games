package maze_fx.models;

public class Field {

    private int x, y;
    private FieldType fieldType;

    public Field(int x, int y, FieldType fieldType) {
        this.x = x;
        this.y = y;
        this.fieldType = fieldType;
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

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public Field clone() {
        return new Field(x, y, fieldType);
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isFieldTyp(FieldType fieldType) {
        return this.fieldType == fieldType;
    }

}
