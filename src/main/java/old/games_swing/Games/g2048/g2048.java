package old.games_swing.Games.g2048;

import old.games_swing.GameLogic.ArrayCopy;
import old.games_swing.Sounds.Sounds;
import javax.swing.JOptionPane;

/**
 * this class contains the main game of 2048 with all logical functions
 * excluding Gui
 *
 * @author Elias
 */
public class g2048 extends AbstractGameSubject {

    private int grid[][], score;//speichern des Spielzustandes
    private final int freeGrids[][];//speichern der Postitionen der freien Grids
    private int countFreeGrids;//speichert die Anzahl der freien Grids
    private boolean over; //speichert ob das Spiel zu Ende ist
    Sounds sound;

    /**
     * creates a new game and sets the variables to default. Creates two random
     * numbers in the Grid
     */
    public g2048() {
        this.over = false;
        this.countFreeGrids = 0;
        this.freeGrids = new int[16][2];
        reset();
        sound = new Sounds();
    }

    /**
     * resets the game and sets the variables to default. Creates two random
     * numbers in the Grid
     */
    public void reset() {
        score = 0;
        over = false;
        grid = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int index = 0; index < 4; index++) {
                grid[index][i] = 0;
            }
        }
        getFreeGrids(grid);
        grid = setRandom(grid);
        getFreeGrids(grid);
        grid = setRandom(grid);
        this.notifyObservers(ArrayCopy.clone2DArray(grid));
    }

    /**
     * @return returns a copy of the current State of the Game
     */
    public int[][] getState() {//liefert eine Kopie des aktuellen Status
        return ArrayCopy.clone2DArray(grid);
    }

    /**
     * @return returns the current score of the Game
     */
    public int getScore() {
        return score;
    }

    /**
     * @return retruns if the Game is over or not -true/false
     */
    public boolean isOver() {
        return over;
    }

    private void hasWonLose() {//calculates if the game is over
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                if (grid[index][i] >= 2048) {//if one of the numbers reached 2048, you won
                    over = true;
                    sound.winBig();
                    JOptionPane.showMessageDialog(null, "Bravo sie haben gewonnen", "2048", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        int scoreBefore = score;//if you can't make any more moves and you haven't yet reached 2048, you lost
        if (!gridChanged(grid, up()) && !gridChanged(grid, down()) && !gridChanged(grid, right()) && !gridChanged(grid, left())) {
            over = true;
            score = scoreBefore;
            sound.lose();
            JOptionPane.showMessageDialog(null, "Sie haben verloren", "2048", JOptionPane.INFORMATION_MESSAGE);
        }
        score = scoreBefore;
    }

    /**
     * contains all permitted moves/directions
     */
    public enum Direction {

        UP, DOWN, LEFT, RIGHT
    }

    /**
     * moves the grid according to the given direction and claculates if the
     * game is over
     *
     * @param direction
     */
    public void move(Direction direction) {
        int resGrid[][] = new int[4][4];
        switch (direction) {
            case UP:
                resGrid = up();
                break;
            case DOWN:
                resGrid = down();
                break;
            case LEFT:
                resGrid = left();
                break;
            case RIGHT:
                resGrid = right();
                break;
        }
        if (gridChanged(grid, resGrid)) {
            getFreeGrids(resGrid);
            grid = setRandom(resGrid);
            this.notifyObservers(ArrayCopy.clone2DArray(grid));

        }
        hasWonLose();
    }

    private int[][] up() {//all numbers will go up and combine when possible
        int[][] egrid = ArrayCopy.clone2DArray(grid);
        for (int xIndex = 0; xIndex < 4; xIndex++) {
            for (int yIndex = 3; yIndex > 0; yIndex--) {
                if (egrid[yIndex - 1][xIndex] == 0) {
                    for (int a = yIndex - 1; a < 3; a++) {
                        egrid[a][xIndex] = egrid[a + 1][xIndex];
                    }
                    egrid[3][xIndex] = 0;
                }
            }
            for (int yIndex = 0; yIndex < 3; yIndex++) {
                if (egrid[yIndex + 1][xIndex] == egrid[yIndex][xIndex]) {
                    score += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex] += egrid[yIndex][xIndex];
                    egrid[yIndex + 1][xIndex] = 0;
                }
            }
            for (int yIndex = 3; yIndex > 0; yIndex--) {
                if (egrid[yIndex - 1][xIndex] == 0) {
                    for (int a = yIndex - 1; a < 3; a++) {
                        egrid[a][xIndex] = egrid[a + 1][xIndex];
                    }
                    egrid[3][xIndex] = 0;
                }
            }
        }
        return ArrayCopy.clone2DArray(egrid);
    }

    private int[][] right() {//all numbers will right and combine when possible
        int[][] egrid = ArrayCopy.clone2DArray(grid);
        for (int yIndex = 0; yIndex < 4; yIndex++) {
            for (int xIndex = 0; xIndex < 3; xIndex++) {
                if (egrid[yIndex][xIndex + 1] == 0) {
                    for (int a = xIndex + 1; a > 0; a--) {
                        egrid[yIndex][a] = egrid[yIndex][a - 1];
                    }
                    egrid[yIndex][0] = 0;
                }
            }
            for (int xIndex = 3; xIndex > 0; xIndex--) {
                if (egrid[yIndex][xIndex] == egrid[yIndex][xIndex - 1]) {
                    score += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex - 1] += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex] = 0;
                }
            }
            for (int xIndex = 0; xIndex < 3; xIndex++) {
                if (egrid[yIndex][xIndex + 1] == 0) {
                    for (int a = xIndex + 1; a > 0; a--) {
                        egrid[yIndex][a] = egrid[yIndex][a - 1];
                    }
                    egrid[yIndex][0] = 0;
                }
            }
        }
        return ArrayCopy.clone2DArray(egrid);
    }

    private int[][] down() {//all numbers will down and combine when possible
        int[][] egrid = ArrayCopy.clone2DArray(grid);
        for (int xIndex = 0; xIndex < 4; xIndex++) {
            for (int yIndex = 0; yIndex < 3; yIndex++) {
                if (egrid[yIndex + 1][xIndex] == 0) {
                    for (int a = yIndex + 1; a > 0; a--) {
                        egrid[a][xIndex] = egrid[a - 1][xIndex];
                    }
                    egrid[0][xIndex] = 0;
                }
            }
            for (int yIndex = 3; yIndex > 0; yIndex--) {
                if (egrid[yIndex - 1][xIndex] == egrid[yIndex][xIndex]) {
                    score += egrid[yIndex][xIndex];
                    egrid[yIndex - 1][xIndex] += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex] = 0;
                }

            }
            for (int yIndex = 0; yIndex < 3; yIndex++) {
                if (egrid[yIndex + 1][xIndex] == 0) {
                    for (int a = yIndex + 1; a > 0; a--) {
                        egrid[a][xIndex] = egrid[a - 1][xIndex];
                    }
                    egrid[0][xIndex] = 0;
                }
            }
        }
        return ArrayCopy.clone2DArray(egrid);
    }

    private int[][] left() {//all numbers will go left and combine when possible
        int[][] egrid = ArrayCopy.clone2DArray(grid);
        for (int yIndex = 0; yIndex < 4; yIndex++) {
            for (int xIndex = 3; xIndex > 0; xIndex--) {
                if (egrid[yIndex][xIndex - 1] == 0) {
                    for (int a = xIndex - 1; a < 3; a++) {
                        egrid[yIndex][a] = egrid[yIndex][a + 1];
                    }
                    egrid[yIndex][3] = 0;
                }
            }
            for (int xIndex = 0; xIndex < 3; xIndex++) {
                if (egrid[yIndex][xIndex] == egrid[yIndex][xIndex + 1]) {
                    score += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex + 1] += egrid[yIndex][xIndex];
                    egrid[yIndex][xIndex] = 0;
                }
            }
            for (int xIndex = 3; xIndex > 0; xIndex--) {
                if (egrid[yIndex][xIndex - 1] == 0) {
                    for (int a = xIndex - 1; a < 3; a++) {
                        egrid[yIndex][a] = egrid[yIndex][a + 1];
                    }
                    egrid[yIndex][3] = 0;
                }
            }
        }
        return ArrayCopy.clone2DArray(egrid);
    }

    private void getFreeGrids(int grid[][]) {//calculates all free Grids
        for (int i = 0; i < 16; i++) {
            freeGrids[i][0] = 0;
            freeGrids[i][1] = 0;
        }
        countFreeGrids = 0;      //number of grids with 0
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                if (grid[index][i] == 0) {
                    freeGrids[countFreeGrids][0] = index;
                    freeGrids[countFreeGrids][1] = i;
                    countFreeGrids++;
                }
            }
        }
    }

    private boolean gridChanged(int gridNeu[][], int gritAlt[][]) {
        for (int index = 0; index < 4; index++) {
            for (int i = 0; i < 4; i++) {
                if (gridNeu[index][i] != gritAlt[index][i]) {
                    return true;
                }
            }
        }//gives back if the Grid has changed or not
        return false;
    }

    private int[][] setRandom(int[][] resGrid) {//sets an random number at one of the free grids
        int a = (int) (Math.random() * countFreeGrids);
        int b = (int) (Math.random() * 10);
        if (b == 5) {
            resGrid[freeGrids[a][0]][freeGrids[a][1]] = 4;
        } else {
            resGrid[freeGrids[a][0]][freeGrids[a][1]] = 2;
        }
        return ArrayCopy.clone2DArray(resGrid);
    }
}
