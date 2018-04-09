package old.games_fx.Games;

import old.games_fx.Games.Maze.Maze_2D.MazeGui;
import old.games_fx.Games.Minessweeper.GUI.Minessweeper_GUI;
import old.games_fx.Sounds.Sounds;
import java.awt.Frame;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Starter starts the aplication
 *
 * @author Elias Schorr, BBBaden
 * @since 16.09.2014
 * @version 1.0
 */
public class Starter extends Application {

    private Sounds sound;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Minessweeper_GUI gui = new Minessweeper_GUI(primaryStage);
        primaryStage.show();
//        Frame frame = new Frame();
//        frame.setVisible(true);
//        MazeGui gui = new MazeGui(frame);
//        gui.newGame();
    }

}
