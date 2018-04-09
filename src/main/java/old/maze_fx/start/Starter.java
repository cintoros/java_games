package old.maze_fx.start;

import old.maze_fx.def.keybordListener.KeyboardListener;
import old.maze_fx.maze.controller.MazeGame;
import old.maze_fx.maze.gui.MazeScene;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author Elias Schorr
 * @since 21.01.2015
 * @version 1.0
 */
public class Starter extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        KeyboardListener k = new KeyboardListener();
        MazeGame c = new MazeGame();
        MazeScene sc = new MazeScene(k, primaryStage, c);
        new Thread(sc).start();
        new Thread(c).start();
        c.addChangeObserver(sc);
        c.newGame();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
