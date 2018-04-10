package maze_fx;

import javafx.application.Application;
import javafx.stage.Stage;
import shared.keybordListener.DirectionListener;
import maze_fx.controller.MazeGame;
import maze_fx.gui.MazeScene;

public class MazeFxMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        MazeGame mazeGame = new MazeGame();
        MazeScene mazeScene = new MazeScene(new DirectionListener(), primaryStage, mazeGame);
        mazeGame.addChangeObserver(mazeScene);
        mazeGame.newGame();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
