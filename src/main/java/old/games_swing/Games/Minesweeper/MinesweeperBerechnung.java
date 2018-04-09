package old.games_swing.Games.Minesweeper;

/**
 * calculation of the Minesweeper game.
 *
 * @author Elias
 */
public class MinesweeperBerechnung {

    private int[][] settingBombs(int height, int lenght, int mines) {
        int[][] bombList = new int[height][lenght];
        int minesCount = 0;
        int variable = (height * lenght) / mines;//mines-possibility
        // Zufallszahl zwischen 1 und Bombenquote wird erstellt und in b gespeichert
        int b = (int) (Math.random() * variable + 1);//random value between 1 and mines possibility.
        while (minesCount < mines) {//as long as it doesn't have enough mines
            for (int index = 0; index < height; index++) {
                for (int i = 0; i < lenght; i++) {
                    int a = (int) (Math.random() * variable + 1);// mines are placed random in empty areas
                    if (a == b && bombList[index][i] != 9) {//saving the position of the bomb
                        bombList[index][i] = 9;
                        minesCount = minesCount + 1;
                    }
                    if (minesCount >= mines) {//if it has enough bomb.
                        break;
                    }
                }
                if (minesCount >= mines) {//if it has enough bomb.
                    break;
                }
            }
        }
        return bombList;
    }

    /**
     * gives back an 2D array that contains the numbers of the bombs arround the
     * specific field. If it is 9 or greater it is an bomb.
     *
     * @param height
     * @param length
     * @param mines
     * @return
     */
    public int[][] calculatingBombs(int height, int length, int mines) {
        int[][] bombList = settingBombs(height, length, mines);
        //calculating of the neigbor bombs
        for (int index = 0; index < height; index++) {
            for (int i = 0; i < length; i++) {
                if (i == 0) {//if the field ist up
                    hasFieldDown(i, index, bombList);//hat es unten ein Feld
                    if (index == 0) {//wenn das Feld links ist hat
                        hasFieldrechts(i, index, bombList);// es rechts ein Feld
                        hasFieldDownRight(i, index, bombList);// es rechts unten ein Feld
                    } else {//ansonsten hat
                        hasFieldlinks(i, index, bombList);//es links ein Feld
                        hasFieldDownLeft(i, index, bombList);//es links unten ein Feld
                        if (index != height - 1) {//wenn das Feld mittig ist hat
                            hasFieldrechts(i, index, bombList);//es rechts ein Feld
                            hasFieldDownRight(i, index, bombList);//es rechts unten ein Feld
                        }
                    }
                } else {
                    hasFieldUp(i, index, bombList);
                    if (i == length - 1) { //wenn das Feld  unten ist
                        if (index == 0) {//wenn das Feld links ist
                            hasFieldrechts(i, index, bombList);
                            hasFieldUpRight(i, index, bombList);
                        } else {
                            hasFieldlinks(i, index, bombList);
                            hasFieldUpLeft(i, index, bombList);
                            if (index != height - 1) {//wenn das Feld mittig ist
                                hasFieldrechts(i, index, bombList);
                                hasFieldUpRight(i, index, bombList);
                            }
                        }
                    } else {//wenn das Feld mittig ist
                        hasFieldDown(i, index, bombList);
                        if (index == 0) {//wenn das Feld links ist
                            hasFieldrechts(i, index, bombList);
                            hasFieldUpRight(i, index, bombList);
                            hasFieldDownRight(i, index, bombList);
                        } else {
                            hasFieldlinks(i, index, bombList);
                            hasFieldUpLeft(i, index, bombList);
                            hasFieldDownLeft(i, index, bombList);
                            if (index != height - 1) {//wenn das Feld mittig ist
                                hasFieldrechts(i, index, bombList);
                                hasFieldUpRight(i, index, bombList);
                                hasFieldDownRight(i, index, bombList);
                            }
                        }
                    }
                }
            }
        }
        return bombList;
    }

    private void hasFieldUp(int i, int index, int[][] bombList) {
        if (bombList[index][i - 1] >= 9) {
            bombList[index][i] += 1;
        }
    }

    private void hasFieldUpRight(int i, int index, int[][] bombList) {
        if (bombList[index + 1][i - 1] >= 9) {
            bombList[index][i] += 1;
        }
    }

    private void hasFieldUpLeft(int i, int index, int[][] bombList) {
        if (bombList[index - 1][i - 1] >= 9) {
            bombList[index][i] += 1;
        }
    }

    private void hasFieldDown(int i, int index, int[][] bombList) {
        if (bombList[index][i + 1] >= 9) {
            bombList[index][i] += 1;
        }
    }

    private void hasFieldDownLeft(int i, int index, int[][] bombList) {
        if (bombList[index - 1][i + 1] >= 9) {
            bombList[index][i] += 1;
        }
    }

    private void hasFieldDownRight(int i, int index, int[][] bombList) {
        if (bombList[index + 1][i + 1] >= 9) {
            bombList[index][i] += 1;
        }
    }

    private void hasFieldrechts(int i, int index, int[][] bombList) {
        if (bombList[index + 1][i] >= 9) {
            bombList[index][i] += 1;
        }
    }

    private void hasFieldlinks(int i, int index, int[][] bombList) {
        if (bombList[index - 1][i] >= 9) {
            bombList[index][i] += 1;
        }
    }
}
