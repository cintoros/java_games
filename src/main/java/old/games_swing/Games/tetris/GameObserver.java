package old.games_swing.Games.tetris;

import java.util.ArrayList;

/**
 *
 * @author Elias
 */
public interface GameObserver {

    /**
     *
     * @param removed
     * @param added
     */
    public void onStateChange(ArrayList<int[]>removed,ArrayList<int[]>added);
}
