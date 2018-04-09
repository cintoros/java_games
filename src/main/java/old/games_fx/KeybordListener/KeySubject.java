package old.games_fx.KeybordListener;

/**
 *
 * @author Elias
 */
public interface KeySubject {
    
    /**
     *
     * @param o
     */
    public void addKeyObserver(KeyObserver o);

    /**
     *
     * @param o
     */
    public void removeKeyObserver(KeyObserver o);
}
