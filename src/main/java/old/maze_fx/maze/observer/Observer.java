package old.maze_fx.maze.observer;

import old.maze_fx.def.keybordListener.ArrowDirection;
import old.maze_fx.maze.models.Field;

/**
 * Interface der Observer-Struktur
 *
 * @author Elias Schorr
 * @since 07.11.2014
 * @version 1.0
 */
public interface Observer {

    public void onPlayerPositionChange(Field player, Field[] surroundings);

    public void newGame(int length, int heigth);

    public void onDirectionChange(ArrowDirection direction);
}
