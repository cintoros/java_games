package shared.keybordListener;

import java.util.HashSet;

public abstract class AbstractDirectionSubject {

    private final HashSet<DirectionObserver> observers = new HashSet<>();

    public void addKeyObserver(DirectionObserver o) {
        observers.add(o);
    }

    protected void notifyObservers(Direction direction) {
        HashSet<DirectionObserver> copy;
        synchronized (observers) {
            copy = new HashSet<>(observers);
        }
        for (DirectionObserver o : copy) {
            o.onStateChange(direction);
        }
    }
}
