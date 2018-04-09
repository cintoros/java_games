package old.games_fx.DefaultGrapics.IndexButton;

import java.util.HashSet;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * @author Elias Schorr, BBBaden
 * @since 02.10.2014
 * @version 1.0
 */
public class IndexButton extends AbstractClickSubject {

    /**
     *
     */
    protected final Button button;
    private final HashSet<ClickObserver> observers = new HashSet();
    private final int xKord, yKord;

    /**
     *
     * @param xKord
     * @param yKord
     */
    public IndexButton(int xKord, int yKord) {
        this.xKord = xKord;
        this.yKord = yKord;
        button = new Button();
        //button.setPrefSize(20, 20);
        // button.autosize();
        button.setOnMouseClicked(new EventHandler() {
            @Override
            public void handle(Event event) {
                clicked((MouseEvent) event);
            }
        });

    }

    private void clicked(MouseEvent e) {
        if (e.getButton() == MouseButton.SECONDARY) {
            notifyObservers(Click.RM, xKord, yKord);
        } else {
            notifyObservers(Click.LM, xKord, yKord);
        }
    }

    /**
     *
     * @return
     */
    public Button getButton() {
        return button;
    }
}
