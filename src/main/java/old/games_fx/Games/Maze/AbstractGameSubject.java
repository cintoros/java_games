package old.games_fx.Games.Maze;

import java.util.HashSet;

/**
 * @author Elias Schorr, BBBaden
 * @since 16.09.2014
 * @version 1.0
 */
public class AbstractGameSubject implements GameSubject {

    private final HashSet<GameObserver> observers = new HashSet();

    /**
     * notifys the Observers of position changes
     *
     */
    protected void notifyObserversOfPositionChange() {
        HashSet<GameObserver> copy;
        synchronized (observers) {
            copy = new HashSet(observers);
        }
        copy.stream().forEach((o) -> {
            o.onPositionChange();
        });
    }

    /**
     * notify the observer that a new game has started.
     */
    protected void notifyNewGame() {
        HashSet<GameObserver> copy;
        synchronized (observers) {
            copy = new HashSet(observers);
        }
        copy.stream().forEach((o) -> {
            o.newGame();
        });
    }

    /**
     * notifiy the observers, that the game is over
     */
    protected void notifyGameOver() {
        HashSet<GameObserver> copy;
        synchronized (observers) {
            copy = new HashSet(observers);
        }
        copy.stream().forEach((o) -> {
            o.onGameOver();
        });
    }

    /**
     * notify the observers of the wnning
     */
    protected void notifyGameWon() {
        HashSet<GameObserver> copy;
        synchronized (observers) {
            copy = new HashSet(observers);
        }
        copy.stream().forEach((o) -> {
            o.onGameOver();
        });
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
