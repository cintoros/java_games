package shared.keybordListener;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DirectionListener extends AbstractDirectionSubject implements EventHandler<KeyEvent> {

    @Override
    public void handle(KeyEvent evt) {
        KeyCode e = evt.getCode();
        if (e == KeyCode.LEFT || e == KeyCode.A) {
            notifyObservers(Direction.Left);
        } else if (e == KeyCode.UP || e == KeyCode.W) {
            notifyObservers(Direction.Up);
        } else if (e == KeyCode.RIGHT || e == KeyCode.D) {
            notifyObservers(Direction.Right);
        } else if (e == KeyCode.DOWN || e == KeyCode.S) {
            notifyObservers(Direction.Down);
        }
    }
}
