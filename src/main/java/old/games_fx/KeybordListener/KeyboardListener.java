package old.games_fx.KeybordListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * an KeyListener that only reacts to WASD UP DOWN LEFT RIGHT to use it have to
 * (implements KeyObserver) to your class and implement the onStateChange
 * methode
 *
 * @author Elias
 */
public class KeyboardListener extends AbstractKeySubject implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
        //Does Nothing
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        ArrowDirection direction = null;
        int e = evt.getKeyCode();
        if (e == KeyEvent.VK_LEFT || e == KeyEvent.VK_A) {
            direction = ArrowDirection.Left;
        } else if (e == KeyEvent.VK_UP || e == KeyEvent.VK_W) {
            direction = ArrowDirection.Up;
        } else if (e == KeyEvent.VK_RIGHT || e == KeyEvent.VK_D) {
            direction = ArrowDirection.Right;
        } else if (e == KeyEvent.VK_DOWN || e == KeyEvent.VK_S) {
            direction = ArrowDirection.Down;
        }
        if (direction != null) {
            this.notifyObservers(direction);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Does Nothing
    }

    /**
     *
     * @return returns the KeyListener that implements
     */
    public KeyListener getListener() {
        return this;
    }
}
