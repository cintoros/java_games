package old.maze_fx.maze.observer;

import old.maze_fx.def.keybordListener.ArrowDirection;
import old.maze_fx.maze.models.Field;
import java.util.HashSet;

/**
 * Klasse der Observer-Struktur
 *
 * @author Elias Schorr, BBBaden
 * @since 07.11.2014
 * @version 1.0
 */
public class AbstractSubject implements Subject {

    private final HashSet<Observer> observers = new HashSet();

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

    protected void notifyDirection(ArrowDirection direction) {
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

    @Override
    public void addChangeObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeChangeObserver(Observer o) {
        observers.remove(o);
    }
}
