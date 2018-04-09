package old.games_fx.Games.Minessweeper.GUI;

import old.games_fx.Games.Minessweeper.State;
import old.games_fx.DefaultGrapics.IndexButton.IndexButton;

/**
 * this class creates an Button and saves its position in an
 *
 * @author Elias Schorr, BBBaden
 * @since 23.09.2014
 * @version 1.0
 */
public class MinesField extends IndexButton {

    /**
     *
     * @param xKord
     * @param yKord
     */
    public MinesField(int xKord, int yKord) {
        super(xKord, yKord);
        button.setText(" ");
        setResult(State.NONE);
        button.getStylesheets().add(MinesField.class.getResource("Minessweeper.css").toExternalForm());
        button.setPrefSize(30, 30);
//        button.setId("" + State.NONE);
    }

    /**
     *
     * @param bombs
     */
    public void setResult(State bombs) {
        button.setId("" + bombs);
        switch (bombs) {
            case NONE: {
                button.setText(" ");
                break;
            }
            case FLAG: {
                button.setText("F");
                break;
            }
            case BOMB: {
                button.setText("B");
                break;
            }
            case NOBOMB: {
                button.setText("X");
                break;
            }
            case ONE: {
                button.setText("1");
                break;
            }
            case TWO: {
                button.setText("2");
                break;
            }
            case THREE: {
                button.setText("3");
                break;
            }
            case FOUR: {
                button.setText("4");
                break;
            }
            case FIVE: {
                button.setText("5");
                break;
            }
            case SIX: {
                button.setText("6");
                break;
            }
            case SEVEN: {
                button.setText("7");
                break;
            }
            case EIGHT: {
                button.setText("8");
                break;
            }
        }
    }
}
