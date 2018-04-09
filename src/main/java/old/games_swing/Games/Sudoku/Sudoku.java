package old.games_swing.Games.Sudoku;

import old.games_swing.GameLogic.ArrayCopy;

/**
 * sudoku the game in 3x3 4x4 or 5x5 size
 *
 * @author Elias
 */
public class Sudoku {

    private int[][] sudoku, solution;//soduku field with and without empty fields
    private int quadsize;//size of one sodukue quadrat
    private boolean end;//game over?

    /**
     * calculates an new Sudoku, with the quadratsize (3-5) and gives it back
     * with some missing numbers depending on the difficulty
     *
     * @param quadsize
     * @param difficulty
     */
    public void newGame(int quadsize, Difficulty difficulty) {
        SudokuCalculating berechnung = new SudokuCalculating();
        solution = berechnung.calculateSudoku(quadsize);
        sudoku = berechnung.setDificulty(difficulty, quadsize, ArrayCopy.clone2DArray(solution));
        this.quadsize = quadsize;
        end = false;
    }

    /**
     * @return returns the actuel sudocu in an 2D Integer Array
     */
    public int[][] getSudoku() {
        return ArrayCopy.clone2DArray(sudoku);
    }

    /**
     * the Variantes of Difficulty the game is playable in
     */
    public enum Difficulty {

        veryEasy, Easy, Normal, hard, veryhard, godlike, legend, god
    }

    /**
     * gives back if the player has the same solutuion how the PC expeced.
     *
     * @param player
     * @return gives back an 2D Array of boolean for every number if, the nuber
     * was true
     */
    public boolean[][] isCorrect(int[][] player) {
        int max = quadsize * quadsize;
        boolean[][] resultat = new boolean[max][max];
        end = true;
        for (int i = 0; i < max; i++) {
            for (int index = 0; index < max; index++) {
                if (solution[i][index] != player[i][index]) {
                    end = false;
                } else {
                    resultat[i][index] = true;
                }
            }
        }
        return resultat;
    }

    /**
     * @return gives back if the player has already solved the game
     */
    public boolean getEnde() {
        return end;
    }

    /**
     * resets the end of the game
     */
    public void reset() {
        end = false;
    }
}
