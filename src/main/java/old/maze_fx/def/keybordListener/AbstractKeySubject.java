package old.maze_fx.def.keybordListener;

import java.util.HashSet;

/**
 *
 * @author Elias
 */
public abstract class AbstractKeySubject implements KeySubject {

    private final HashSet<KeyObserver> observers = new HashSet();

    /**
     * adds the KeyObserver o
     *
     * @param o
     */
    @Override
    public void addKeyObserver(KeyObserver o) {
        observers.add(o);
    }

    /**
     * removes the KeyObserver o
     *
     * @param o
     */
    @Override
    public void removeKeyObserver(KeyObserver o) {
        observers.remove(o);
    }

    /**
     * notifys the Observers
     *
     * @param direction
     */
    protected void notifyObservers(ArrowDirection direction) {
        HashSet<KeyObserver> copy;
        synchronized (observers) {
            copy = new HashSet(observers);
        }
        for (KeyObserver o : copy) {
            o.onStateChange(direction);
        }
    }
}
