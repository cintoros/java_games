package old.games_fx.DefaultGrapics.ChangingLabel;

import javafx.scene.control.Label;

/**
 * @author Elias Schorr, BBBaden
 * @since 16.09.2014
 * @version 1.0
 */
public class ChangingLabel implements ChangeObserver {

    private final Label label;

    /**
     *
     * @param start
     */
    public ChangingLabel(String start) {
        label = new Label(start);
    }

    @Override
    public void onStateChange(String text) {
        label.setText(text);
    }

    /**
     *
     * @return
     */
    public Label getLabel() {
        return label;
    }
}
