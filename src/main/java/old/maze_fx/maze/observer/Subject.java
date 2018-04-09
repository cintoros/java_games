package old.maze_fx.maze.observer;

/**
 * Interface der Observer-Struktur
 *
 * @author Elias Schorr, BBBaden
 * @author Anessollah Ima
 * @since 07.11.2014
 * @version 1.0
 */
public interface Subject {

    /**
     * fügt dem Subject einen Observer hinzu
     *
     * @param o der Observer
     */
    public void addChangeObserver(Observer o);

    /**
     * entfernt den Observer
     *
     * @param o der Observer
     */
    public void removeChangeObserver(Observer o);
}
