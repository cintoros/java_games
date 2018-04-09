package old.games_fx.DefaultGrapics;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * an Scene with an Status and Menu Bar
 *
 * @author Elias Schorr, BBBaden
 * @since 02.10.2014
 * @version 1.0
 */
public class DefaultScene {

    private final HBox menuBar, statusBar;
    private final BorderPane border;
    ScrollPane pane;
    private final Scene scene;

    /**
     *
     * @param stage
     */
    public DefaultScene(Stage stage) {
        this.pane = new ScrollPane();
        //init
        this.statusBar = new HBox();
        this.menuBar = new HBox();
        border = new BorderPane();
        scene = new Scene(border);
        //order
        border.setTop(menuBar);
        border.setBottom(statusBar);
        stage.setScene(scene);
        border.setCenter(pane);
    }

    /**
     *
     * @param n
     */
    public void addToMenuBar(Node n) {
        menuBar.getChildren().add(n);
    }

    /**
     *
     * @param n
     */
    public void addToStatusBar(Node n) {
        statusBar.getChildren().add(n);
    }

    /**
     *
     * @param n
     */
    public void setCenter(Node n) {
        pane.setContent(n);
    }

    /**
     *
     * @param n
     */
    public void removeFromMenuBar(Node n) {
        menuBar.getChildren().remove(n);
    }

    /**
     *
     * @param n
     */
    public void removeFromStatusBar(Node n) {
        statusBar.getChildren().remove(n);
    }
    public Scene getScene() {
        return scene;
    }

}
