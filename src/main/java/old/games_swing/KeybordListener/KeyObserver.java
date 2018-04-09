package old.games_swing.KeybordListener;

import old.games_swing.KeybordListener.KeyboardListener.ArrowDirection;

/**
 *
 * @author Elias
 */
public interface KeyObserver {

    /**
     *
     * @param direction
     */
    public void onStateChange(ArrowDirection direction);
}
