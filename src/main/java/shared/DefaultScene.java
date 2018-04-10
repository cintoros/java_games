package shared;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import shared.keybordListener.DirectionListener;

/**
 * Scene with an Status and Menu Bar
 */
public class DefaultScene {

    private final HBox menuBar, statusBar;
    private final BorderPane border;
    private final Scene scene;
    private final ScrollPane scroll;

    public DefaultScene(Stage stage) {
        //init
        this.statusBar = new HBox();
        this.menuBar = new HBox();
        border = new BorderPane();
        scene = new Scene(border);
        //order
        border.setTop(menuBar);
        border.setBottom(statusBar);
        stage.setScene(scene);
        scroll = new ScrollPane();
        border.setCenter(scroll);

        scene.getStylesheets()
                .add("format.css");
    }

    public void addToMenuBar(Node n) {
        menuBar.getChildren()
                .add(n);
    }

    public void addToStatusBar(Node n) {
        statusBar.getChildren()
                .add(n);
    }

    public void setCenter(Node n) {
        scroll.setContent(n);
    }

    public void removeFromMenuBar(Node n) {
        menuBar.getChildren()
                .remove(n);
    }

    public void removeFromStatusBar(Node n) {
        statusBar.getChildren()
                .remove(n);
    }

    public Scene getScene() {
        return scene;
    }

    public void setonKeyPressed(DirectionListener k) {
        scene.setOnKeyPressed(k);
        scroll.setOnKeyPressed(k);
    }
}
