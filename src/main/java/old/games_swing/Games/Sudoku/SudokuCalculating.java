package old.games_swing.Games.Sudoku;

import old.games_swing.GameLogic.ArrayCopy;
import old.games_swing.Games.Sudoku.Sudoku.Difficulty;
import java.util.ArrayList;
import java.util.Random;

/**
 * this class calculates the sudokus in the sizes 3x3 4x4 and 5x5
 *
 * @author Elias
 */
public class SudokuCalculating {

    private int[][] sudoku;//saving of the sudoku
    private int max;//saving of the max possible number with the quadsize

    /**
     * calculates an new Sudoku, with the quadratsize (3-5)
     *
     * @param quadsize
     * @return a new sudoku
     */
    public int[][] calculateSudoku(int quadsize) {
        max = (quadsize * quadsize);//setting f max
        while (true) {//callculating until it gives a break(has a good Sudoku)
            boolean good = true;//in the beggining every soduku is good
            int counter = 0;//setting of counter
            sudoku = new int[max][max];//field creation and some other saving variables
            int[] row = new int[max];
            ArrayList array = new ArrayList(max);
            Random random = new Random();
            int xIndex = 0;
            for (int yIndex = 0; yIndex < max; yIndex++) {//an random row is created
                while (true) {
                    int zahl = random.nextInt(max) + 1;
                    if (isCorrect(xIndex, yIndex, quadsize, zahl)) {//when the numbers in the row are good
                        sudoku[xIndex][yIndex] = zahl;
                        row[yIndex] = zahl;
                        break;//continue with the next step
                    }
                }
            }
            for (int i = 1; i < max; i++) {//the row is cloned as often as meeded
                int tmp = row[0];
                for (int index = 0; index < max - 1; index++) {//the rows are moved a cell
                    row[index] = row[index + 1];
                }
                row[max - 1] = tmp;
                array.add(row.clone());
            }
            for (xIndex = 1; xIndex < max; xIndex++) {
                while (true) {//until a good soduku is created a random row is picked and setted.
                    int b = random.nextInt(array.size());
                    int zufallsspalte[] = (int[]) array.get(b);
                    System.arraycopy(zufallsspalte, 0, sudoku[xIndex], 0, max);//cheching if the sudoku is correct
                    if (isCorrect(xIndex, 0, quadsize, zufallsspalte[0]) && isCorrect(xIndex, 1, quadsize, zufallsspalte[1]) && isCorrect(xIndex, 2, quadsize, zufallsspalte[2]) && isCorrect(xIndex, 3, quadsize, zufallsspalte[3]) && isCorrect(xIndex, 4, quadsize, zufallsspalte[4])) {
                        counter = 0;
                        array.remove(b);
                        break;
                    }
                    counter++;
                    if (counter > 30) {//if it isn't good no matter what calcluate it again
                        good = false;
                        counter = 0;
                        break;
                    }
                    if (!good) {
                        break;
                    }
                }
            }
            if (good) {//if it is good give a clone back
                return ArrayCopy.clone2DArray(sudoku);
            }
        }
    }

    private boolean isCorrect(int xIndex, int yIndex, int quadsize, int number) {//calculates if the new number is correct
        for (int i = 0; i < max; i++) {//looks if there is an identical number in the collon
            if (sudoku[i][yIndex] == number) {
                if (i != xIndex) {
                    return false;
                }
            }
        }
        for (int i = 0; i < max; i++) {//looks if there is an identical number in the row
            if (sudoku[xIndex][i] == number) {
                if (i != yIndex) {
                    return false;
                }
            }
        }
        int xStart, yStart;
        if (xIndex < quadsize * 1) {//looks in witch quadrat/box on the X-Kordinate the number is an saves it
            xStart = 0;
        } else if (xIndex < quadsize * 2) {
            xStart = quadsize;
        } else if (xIndex < quadsize * 3) {
            xStart = quadsize * 2;
        } else if (xIndex < quadsize * 4) {
            xStart = quadsize * 3;
        } else {
            xStart = quadsize * 4;
        }
        if (yIndex < quadsize * 1) {//looks in witch quadrat/box on the Y-Kordinate the number is an saves it
            yStart = 0;
        } else if (yIndex < quadsize * 2) {
            yStart = quadsize;
        } else if (yIndex < quadsize * 3) {
            yStart = quadsize * 2;
        } else if (yIndex < quadsize * 4) {
            yStart = quadsize * 3;
        } else {
            yStart = quadsize * 4;
        }
        for (int i = xStart; i < xStart + quadsize; i++) {//looks if there is an identical number in the same quadrat/box
            for (int index = yStart; index < yStart + quadsize; index++) {
                if (sudoku[i][index] == number) {
                    if (i != xIndex || index != yIndex) {
                        return false;
                    }
                }
            }
        }//if the number doesn't violate any rules the method gives back true
        return true;
    }

    /**
     * sets the free fields
     *
     * @param diffi
     * @param quadsize
     * @param sudoku
     * @return returns the sudoku with empty fields
     */
    public int[][] setDificulty(Difficulty diffi, int quadsize, int[][] sudoku) {
        int emptyFields = 0;
        switch (diffi) {//sets the empty field based on the difficulty
            case veryEasy: {
                emptyFields = max * (max / 6);
                break;
            }
            case Easy: {
                emptyFields = max * (max / 5);
                break;
            }
            case Normal: {
                emptyFields = max * (max / 4);
                break;
            }
            case hard: {
                emptyFields = max * (max / 3);
                break;
            }
            case veryhard: {
                emptyFields = max * (max / 2);
                break;
            }
            case godlike: {
                emptyFields = max * (int) (max / 1.5);
                break;
            }
            case legend: {
                emptyFields = max * (int) (max / 1.25);
                break;
            }
            case god: {
                emptyFields = max * (int) (max / 1.1);
                break;
            }
        }
        Random random = new Random();//some random fields are set emty until it has enough
        for (int delete = emptyFields; delete > 0; delete--) {
            while (true) {
                int xIndex = random.nextInt(max);
                int yIndex = random.nextInt(max);
                if (sudoku[xIndex][yIndex] != 0) {
                    sudoku[xIndex][yIndex] = 0;
                    break;
                }
            }
        }//returns an clone of the sudoku
        return ArrayCopy.clone2DArray(sudoku);
    }
}
