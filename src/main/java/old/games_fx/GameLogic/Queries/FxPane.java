package old.games_fx.GameLogic.Queries;

import old.games_fx.DefaultGrapics.DefaultScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Elias Schorr, BBBaden
 * @since 21.10.2014
 * @version 1.0
 */
public class FxPane implements Queries {

    private final Scene child;
    private final DefaultScene scene;
    private final Stage stage;
    private final Button yesButton, noButton;
    private final Label question, title;
    private final GridPane pane;
    private final TextField field;

    public FxPane(Scene child, Stage stage) {
        this.child = child;
        this.stage = stage;
        scene = new DefaultScene(stage);
        stage.setScene(child);
        yesButton = new Button("Yes");
        noButton = new Button("No");
        question = new Label("question");
        title = new Label("question");
        scene.addToMenuBar(title);
        pane = new GridPane();
        pane.add(question, 0, 0);
        pane.add(yesButton, 1, 0);
        pane.add(noButton, 1, 1);
        field = new TextField("input");
    }

    @Override
    public options askYesNo(String question, String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showMessage(String message, String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] askNewSizes(int min, int max, String titel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int askSize(String sizeName, int min, int max, String titel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String askSomething(String question, String titel) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
