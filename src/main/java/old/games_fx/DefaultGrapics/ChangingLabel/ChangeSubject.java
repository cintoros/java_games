package old.games_fx.DefaultGrapics.ChangingLabel;

/**
 * F
 * @author Elias Schorr, BBBaden
 * @since 16.09.2014
 * @version 1.0
 */
public interface ChangeSubject {

    /**
     *
     * @param o the observer to add
     */
    public void addChangeObserver(ChangeObserver o);

    /**
     *
     * @param o the observer to remove
     */
    public void removeChangeObserver(ChangeObserver o);
}
