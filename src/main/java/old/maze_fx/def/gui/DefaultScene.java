package old.maze_fx.def.gui;

import old.maze_fx.def.keybordListener.KeyboardListener;
import old.maze_fx.maze.gui.MazeScene;
import old.maze_fx.maze.gui.m2d.Maze2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * an Scene with an Status and Menu Bar
 *
 * @author Elias Schorr
 * @since 02.10.2014
 * @version 1.0
 */
public class DefaultScene {

    private final HBox menuBar, statusBar;
    private final BorderPane border;
    private final Scene scene;
    private final ScrollPane scroll;

    /**
     * inti of the scene
     *
     * @param stage the stage to be displayed on
     */
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
        scene.getStylesheets().add(ClassLoader.getSystemResource("Format.css").toExternalForm());
    }

    /**
     * adds the object to the menu
     *
     * @param n the object
     */
    public void addToMenuBar(Node n) {
        menuBar.getChildren().add(n);
    }

    /**
     * adds the object to the status
     *
     * @param n the object
     */
    public void addToStatusBar(Node n) {
        statusBar.getChildren().add(n);
    }

    /**
     * adds the object to the center
     *
     * @param n the object
     */
    public void setCenter(Node n) {
        scroll.setContent(n);
    }

    /**
     * removes the object from the menu
     *
     * @param n the object
     */
    public void removeFromMenuBar(Node n) {
        menuBar.getChildren().remove(n);
    }

    /**
     * removes the object from the status
     *
     * @param n the object
     */
    public void removeFromStatusBar(Node n) {
        statusBar.getChildren().remove(n);
    }

    /**
     * gives the scene back
     *
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * adds the KeyboardListender to this scene
     *
     * @param k the KeyboardListener
     */
    public void setonKeyPressed(KeyboardListener k) {
        scene.setOnKeyPressed(k);
        scroll.setOnKeyPressed(k);
    }
}
