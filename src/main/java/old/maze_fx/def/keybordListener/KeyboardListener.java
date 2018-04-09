package old.maze_fx.def.keybordListener;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * an KeyListener that only reacts to WASD UP DOWN LEFT RIGHT to use it have to
 * (implements EventHandler<KeyEvent>) to your class and implement the
 * onStateChange methode
 *
 * @author Elias
 */
public class KeyboardListener extends AbstractKeySubject implements EventHandler<KeyEvent> {

    @Override
    public void handle(KeyEvent evt) {
        ArrowDirection direction = null;
        KeyCode e = evt.getCode();
        if (e == KeyCode.LEFT || e == KeyCode.A) {
            direction = ArrowDirection.Left;
        } else if (e == KeyCode.UP || e == KeyCode.W) {
            direction = ArrowDirection.Up;
        } else if (e == KeyCode.RIGHT || e == KeyCode.D) {
            direction = ArrowDirection.Right;
        } else if (e == KeyCode.DOWN || e == KeyCode.S) {
            direction = ArrowDirection.Down;
        }
        if (direction != null) {
            this.notifyObservers(direction);
        }
    }
}
