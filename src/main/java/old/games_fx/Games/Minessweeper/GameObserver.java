package old.games_fx.Games.Minessweeper;

/**
 * @author Elias Schorr, BBBaden
 * @since 16.09.2014
 * @version 1.0
 */
public interface GameObserver {

    /**
     * action when state is changed
     *
     * @param state the new state
     * @param xKord the x point
     * @param yKord the y point
     */
    public void onStateChange(State state, int xKord, int yKord);

    /**
     *
     * @param heigth
     * @param length
     */
    public void newGame(int heigth, int length);

    /**
     * action on game over
     */
    public void onGameOver();

    /**
     * action on game lost
     */
    public void onGameWon();
}
