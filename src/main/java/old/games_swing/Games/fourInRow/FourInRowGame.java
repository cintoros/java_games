package old.games_swing.Games.fourInRow;

import old.games_swing.Games.fourInRow.Stone.StoneColor;

/**
 * the logic behind the 4 in a row game
 * @author Elias
 */
public class FourInRowGame {

    private final StoneColor[][] gameField;//saves the Colors of every stone in the gameField
    private boolean end;//saves if the game is over
    private final FourInRow parent;//saves the guis class

    /**
     * creates an new game
     *
     * @param parentImport
     */
    public FourInRowGame(FourInRow parentImport) {
        parent = parentImport;
        gameField = new Stone.StoneColor[6][7];
        reset();
    }

    /**
     * Tries to drop the stone at the given position.
     *
     * @param position
     * @param player
     * @return returns if the move was succesfull or not
     */
    public boolean setStone(int position, StoneColor player) {
        if (gameField[0][position] == StoneColor.White) {//if the stone is already  occuped return false
            gameField[0][position] = player;//else paint it
            parent.redrawStone(0, position, gameField[0][position]);
            for (int index = 0; index < 5; index++) {//try for the other stones
                if (gameField[index + 1][position] == StoneColor.White) {
                    //jedes mal wenn der Stein einen weiter runterfällt soll dieser auf dem Feld dargestellt werden und ein Thread sleep aufgerufen werden.
                    gameField[index][position] = StoneColor.White;
                    gameField[index + 1][position] = player;
                    parent.redrawStone(index, position, gameField[index][position]);//repaint
                    parent.redrawStone(index + 1, position, player);
                } else {
                    break;//when the sone isn't white break
                }
            }
        } else {
            return false;//if the move is not possible
        }
        return true;//if everything went good return true
    }

    /**
     * @return returns the GameField
     */
    public StoneColor[][] getGameField() {
        return gameField;
    }

    /**
     * resets the game field and the end of the game
     */
    public void reset() {
        for (int index = 0; index < 7; index++) {
            for (int i = 0; i < 6; i++) {
                gameField[i][index] = Stone.StoneColor.White;
            }
        }
        end = false;
    }

    /**
     * @return returns if the game is over(true) or not(false)
     */
    public boolean isOver() {
        return end;
    }

    /**
     * method looks if the Color of the player has 4 in a row.
     *
     * @param player
     * @return gives back if the player has won
     */
    public boolean hasWon(StoneColor player) {
        for (int xIndex = 0; xIndex < 7; xIndex++) {
            for (int yIndex = 0; yIndex < 6; yIndex++) {
                if (gameField[yIndex][xIndex] == player) {
                    if (has4inRow(xIndex, yIndex, player)) {
                        end = true;//if it has a row return true
                        return true;
                    }
                }
            }
        }//else it is false
        return false;
    }

    private boolean has4inRow(int xIndex, int yIndex, StoneColor player) {
        if (xIndex >= 3) {//looks if there are 4 in a row
            if (gameField[yIndex][xIndex - 1] == player && gameField[yIndex][xIndex - 2] == player && gameField[yIndex][xIndex - 3] == player) {
                return true;
            }
        }
        if (yIndex >= 3) {//looks if there are 4 in a row
            if (gameField[yIndex - 1][xIndex] == player && gameField[yIndex - 2][xIndex] == player && gameField[yIndex - 3][xIndex] == player) {
                return true;
            }
        }
        if ((yIndex >= 3) && (xIndex < 4)) {//looks if there are 4 in a diagonal
            if (gameField[yIndex - 1][xIndex + 1] == player && gameField[yIndex - 2][xIndex + 2] == player && gameField[yIndex - 3][xIndex + 3] == player) {
                return true;
            }
        }
        if ((yIndex >= 3) && (xIndex >= 3)) {//looks if there are 4 in a diagonal
            if (gameField[yIndex - 1][xIndex - 1] == player && gameField[yIndex - 2][xIndex - 2] == player && gameField[yIndex - 3][xIndex - 3] == player) {
                return true;
            }
        }//if there isn't a row return false
        return false;
    }
}
