package maze_fx.models;

import maze_fx.controller.MazeCalculation;

public class Maze {

    private MazeCalculation cal = new MazeCalculation();
    private Field[][] fields;
    private int length, heigth;

    public Field getField(int x, int y) {
        try {
            return fields[x][y].clone();
        } catch (Exception e) {//FIXME why???
            return null;
        }
    }

    public Field[][] getFields() {
        return fields;
    }

    public void newMaze(int length, int heigth) {
        this.length = length;
        this.heigth = heigth;
        toField(cal.newMaze(length, heigth));
    }

    public int getHeigth() {
        return heigth;
    }

    public int getLength() {
        return length;
    }

    private void toField(boolean[][] bo) {
        fields = new Field[bo.length][bo[0].length];
        for (int i = 0; i < bo.length; i++) {
            for (int j = 0; j < bo[i].length; j++) {
                if (bo[i][j]) {
                    fields[i][j] = new Field(i, j, FieldType.WAY);
                } else {
                    fields[i][j] = new Field(i, j, FieldType.WALL);
                }
            }
        }
        fields[0][0].setFieldType(FieldType.START);
        fields[bo.length - 1][bo[0].length - 1].setFieldType(FieldType.GOAL);
    }

}
