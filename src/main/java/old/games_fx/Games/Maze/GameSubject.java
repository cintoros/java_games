package old.games_fx.Games.Maze;

/**
 * @author Elias Schorr, BBBaden
 * @since 16.09.2014
 * @version 1.0
 */
public interface GameSubject {

    /**
     *
     * @param o the observer to add
     */
    public void addChangeObserver(GameObserver o);

    /**
     *
     * @param o the observer to remove
     */
    public void removeChangeObserver(GameObserver o);
}
