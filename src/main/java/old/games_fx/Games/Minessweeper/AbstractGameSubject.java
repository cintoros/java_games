package old.games_fx.Games.Minessweeper;

import java.util.HashSet;

/**
 * @author Elias Schorr, BBBaden
 * @since 16.09.2014
 * @version 1.0
 */
public class AbstractGameSubject implements GameSubject {

    private final HashSet<GameObserver> observers = new HashSet();

    /**
     * notifys the Observers
     *
     * @param state
     * @param xKord
     * @param yKord
     */
    protected void notifyObservers(State state, int xKord, int yKord) {
        HashSet<GameObserver> copy;
        synchronized (observers) {
            copy = new HashSet(observers);
        }
        for (GameObserver o : copy) {
            o.onStateChange(state, xKord, yKord);
        }
    }

    /**
     *
     * @param heigth
     * @param length
     */
    protected void notifyNewGame(int heigth, int length) {
        HashSet<GameObserver> copy;
        synchronized (observers) {
            copy = new HashSet(observers);
        }
        for (GameObserver o : copy) {
            o.newGame(heigth, length);
        }
    }

    /**
     * notifiys the observers that the game is over
     */
    protected void notifyGameOver() {
        HashSet<GameObserver> copy;
        synchronized (observers) {
            copy = new HashSet(observers);
        }
        for (GameObserver o : copy) {
            o.onGameOver();
        }
    }

    /**
     * notifys the observers that the game is won
     */
    protected void notifyGameWon() {
        HashSet<GameObserver> copy;
        synchronized (observers) {
            copy = new HashSet(observers);
        }
        for (GameObserver o : copy) {
            o.onGameOver();
        }
    }

    @Override
    public void addChangeObserver(GameObserver o) {
        observers.add(o);
    }

    @Override
    public void removeChangeObserver(GameObserver o) {
        observers.remove(o);
    }

}
