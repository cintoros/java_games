package old.games_fx.DefaultGrapics.IndexButton;

/**
 * @author Elias Schorr, BBBaden
 * @since 25.09.2014
 * @version 1.0
 */
public interface ClickSubject {

    /**
     *
     * @param o the observer to add
     */
    public void addChangeObserver(ClickObserver o);

    /**
     *
     * @param o the observer to remove
     */
    public void removeChangeObserver(ClickObserver o);
}
