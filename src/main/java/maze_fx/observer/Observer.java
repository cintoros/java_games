package maze_fx.observer;

import maze_fx.models.Field;
import shared.keybordListener.Direction;

public interface Observer {

    void onPlayerPositionChange(Field player, Field[] surroundings);

    void newGame(int length, int heigth);

    void onDirectionChange(Direction direction);
}
