package maze_fx.observer;

import java.util.HashSet;
import maze_fx.models.Field;
import shared.keybordListener.Direction;

public class AbstractSubject {

    private final HashSet<Observer> observers = new HashSet<>();

    protected void notifyChange(Field player, Field[] surroundings) {
        for (Observer o : copyHasSet()) {
            o.onPlayerPositionChange(player, surroundings);
        }
    }

    protected void notifyNewGame(int length, int heigth) {
        for (Observer o : copyHasSet()) {
            o.newGame(length, heigth);
        }
    }

    protected void notifyDirection(Direction direction) {
        for (Observer o : copyHasSet()) {
            o.onDirectionChange(direction);
        }
    }

    private HashSet<Observer> copyHasSet() {
        HashSet<Observer> copy;
        synchronized (observers) {
            copy = new HashSet(observers);
        }
        return copy;
    }

    public void addChangeObserver(Observer o) {
        observers.add(o);
    }

}
