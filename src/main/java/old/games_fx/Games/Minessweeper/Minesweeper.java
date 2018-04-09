package old.games_fx.Games.Minessweeper;

import old.games_fx.DefaultGrapics.ChangingLabel.ChangeObserver;
import old.games_fx.DefaultGrapics.IndexButton.Click;
import old.games_fx.GameLogic.ScoreCounter;
import old.games_fx.GameLogic.TimeCalculation;
import static old.games_fx.Games.Minessweeper.State.*;

/**
 * the Class of Minesweeper can be loaded in Frame or in an Applet
 *
 * @author Elias
 */
public class Minesweeper extends AbstractGameSubject {

    private boolean isOver = false;
    private final TimeCalculation time;
    private final ScoreCounter score;
    private int bombs, length, height;
    private int[][] game;
    private State[][] gameState;

    /**
     * constructor
     */
    public Minesweeper() {
        score = new ScoreCounter(3);
        score.setMessage(1, "bombs: ");
        score.setMessage(2, "flags: ");
        score.setMessage(3, "remaining: ");
        time = new TimeCalculation();
    }

    /**
     *
     * @param heigth
     * @param length
     * @param bombs
     */
    public void newGame(int heigth, int length, int bombs) {
        this.length = length;
        this.height = heigth;
        this.bombs = bombs;
        game = MinesweeperBerechnung.calculatingBombs(height, length, bombs);
        gameState = new State[height][length];
        reset();
    }

    /**
     * resets the game
     */
    public void reset() {
        for (int i = 0; i < length; i++) {
            for (int index = 0; index < height; index++) {
                gameState[index][i] = NONE;
            }
        }
        isOver = false;
        score.setScore(1, bombs);
        score.setScore(2, 0);
        score.setScore(3, (height * length - bombs));

        time.reset();
        time.start();
        notifyNewGame(length, height);
    }

    /**
     *
     * @param clicked
     * @param xKord
     * @param yKord
     */
    public void onStateChange(Click clicked, int xKord, int yKord) {
        if (!isOver) {
            try {
                if (clicked == Click.RM) {
                    if (gameState[xKord][yKord] == NONE) {
                        gameState[xKord][yKord] = FLAG;
                        notifyObservers(FLAG, xKord, yKord);
                        score.addPoints(2, 1);
                    } else if (gameState[xKord][yKord] == FLAG) {
                        gameState[xKord][yKord] = NONE;
                        notifyObservers(NONE, xKord, yKord);
                        score.removePoints(2, 1);
                    } else {
                    }
                } else {
                    if (gameState[xKord][yKord] == NONE) {
                        makeNext(clicked, xKord, yKord);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    private void makeNext(Click clicked, int xKord, int yKord) {
        State st = getState(xKord, yKord);
        notifyObservers(st, xKord, yKord);
        if (gameState[xKord][yKord] != st) {
            gameState[xKord][yKord] = st;
            if (st == BOMB) {
                notifyGameOver();
                isOver = true;
            }
            if (st != BOMB || gameState[xKord][yKord] == NONE) {
                score.removePoints(3, 1);
                if (score.getPointsof(3) == 0) {
                    notifyGameWon();
                    isOver = true;
                }
            }
            gameState[xKord][yKord] = st;
            if (st == NOBOMB) {
                onStateChange(clicked, xKord - 1, yKord);
                onStateChange(clicked, xKord + 1, yKord);
                onStateChange(clicked, xKord - 1, yKord + 1);
                onStateChange(clicked, xKord, yKord + 1);
                onStateChange(clicked, xKord + 1, yKord + 1);
                onStateChange(clicked, xKord, yKord - 1);
                onStateChange(clicked, xKord + 1, yKord - 1);
                onStateChange(clicked, xKord - 1, yKord - 1);
            }
        }
    }

    /**
     *
     * @param xKord
     * @param yKord
     * @return
     */
    public State getState(int xKord, int yKord) {
        if (gameState[xKord][yKord] == NONE) {
            switch (game[xKord][yKord]) {
                case 0: {
                    return NOBOMB;
                }
                case 1: {
                    return ONE;
                }
                case 2: {
                    return TWO;
                }
                case 3: {
                    return THREE;
                }
                case 4: {
                    return FOUR;
                }
                case 5: {
                    return FIVE;
                }
                case 6: {
                    return SIX;
                }
                case 7: {
                    return SEVEN;
                }
                case 8: {
                    return EIGHT;
                }
                case 9: {
                    return BOMB;
                }
            }
            return BOMB;
        } else {
            return gameState[xKord][yKord];
        }
    }

    /**
     *
     * @return
     */
    public boolean isOver() {
        return isOver;
    }

    /**
     *
     * @param o
     */
    public void addScoreObserver(ChangeObserver o) {
        score.addChangeObserver(o);
        score.updateScore();
    }

    /**
     *
     * @param o
     */
    public void addTimeObserver(ChangeObserver o) {
        time.addChangeObserver(o);
        time.updateTime();
    }
}
