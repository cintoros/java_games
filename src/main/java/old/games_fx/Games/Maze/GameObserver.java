package old.games_fx.Games.Maze;

/**
 * @author Elias Schorr, BBBaden
 * @since 16.09.2014
 * @version 1.0
 */
public interface GameObserver {

    /**
     * used when position changes
     */
    public void onPositionChange();

    /**
     * used for a new game
     */
    public void newGame();

    /**
     * used on game over
     */
    public void onGameOver();

    /**
     * used on game won
     */
    public void onGameWon();
}
