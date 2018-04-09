package old.games_swing.Games.tetris;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Elias
 */
public abstract class AbstractGameSubject implements GameSubject {

    private final HashSet<GameObserver> observers = new HashSet();

    /**
     * adds the GameObserver o
     *
     * @param o
     */
    @Override
    public void addGameObserver(GameObserver o) {
        observers.add(o);
    }

    /**
     * removes the GameObserver o
     *
     * @param o
     */
    @Override
    public void removeGameObserver(GameObserver o) {
        observers.remove(o);
    }

    /**
     * notifys the Observers
     *
     * @param removed
     * @param added
     */
    protected void notifyObservers(ArrayList<int[]> removed,ArrayList<int[]>added) {
        HashSet<GameObserver> copy;
        synchronized (observers) {
            copy = new HashSet(observers);
        }
        for (GameObserver o : copy) {
            o.onStateChange(removed,added);
        }
    }
}
