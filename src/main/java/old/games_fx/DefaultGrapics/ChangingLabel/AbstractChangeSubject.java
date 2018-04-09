package old.games_fx.DefaultGrapics.ChangingLabel;

import java.util.HashSet;

/**
 * @author Elias Schorr, BBBaden
 * @since 16.09.2014
 * @version 1.0
 */
public class AbstractChangeSubject implements ChangeSubject {

    private final HashSet<ChangeObserver> observers = new HashSet();

    /**
     * notifys the Observers
     *
     * @param text
     */
    protected void notifyObservers(String text) {
        HashSet<ChangeObserver> copy;
        synchronized (observers) {
            copy = new HashSet(observers);
        }
        for (ChangeObserver o : copy) {
            o.onStateChange(text);
        }
    }

    @Override
    public void addChangeObserver(ChangeObserver o) {
        observers.add(o);
    }

    @Override
    public void removeChangeObserver(ChangeObserver o) {
        observers.remove(o);
    }

}
