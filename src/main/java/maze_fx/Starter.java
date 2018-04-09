package maze_fx;

import javafx.application.Application;
import javafx.stage.Stage;
import old.maze_fx.def.keybordListener.KeyboardListener;
import old.maze_fx.maze.controller.MazeGame;
import old.maze_fx.maze.gui.MazeScene;

public class Starter extends Application {

    @Override
    public void start(Stage primaryStage) {
        MazeGame mazeGame = new MazeGame();
        MazeScene mazeScene = new MazeScene(new KeyboardListener(), primaryStage, mazeGame);
        mazeGame.addChangeObserver(mazeScene);
        mazeGame.newGame();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
