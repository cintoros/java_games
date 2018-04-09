package old.games_fx.DefaultGrapics.IndexButton;

import java.util.HashSet;

/**
 * @author Elias Schorr, BBBaden
 * @since 25.09.2014
 * @version 1.0
 */
public class AbstractClickSubject implements ClickSubject {

    private final HashSet<ClickObserver> observers = new HashSet();

    /**
     * notifys the Observers
     *
     * @param click
     * @param xKord
     * @param yKord
     */
    protected void notifyObservers(Click click, int xKord, int yKord) {
        HashSet<ClickObserver> copy;
        synchronized (observers) {
            copy = new HashSet(observers);
        }
        for (ClickObserver o : copy) {
            o.onStateChange(click, xKord, yKord);
        }
    }

    @Override
    public void addChangeObserver(ClickObserver o) {
        observers.add(o);
    }

    @Override
    public void removeChangeObserver(ClickObserver o) {
        observers.remove(o);
    }

}
