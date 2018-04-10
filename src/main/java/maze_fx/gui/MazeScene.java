package maze_fx.gui;

import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import maze_fx.gui.m3d.Maze3D;
import maze_fx.models.Field;
import maze_fx.observer.Observer;
import shared.DefaultScene;
import shared.keybordListener.Direction;
import shared.keybordListener.DirectionListener;
import maze_fx.controller.MazeGame;
import maze_fx.gui.m2d.Maze2D;

/**
 * @author Elias Schorr
 * @version 1.0
 * @since 22.01.2015
 */
public class MazeScene implements Observer, Runnable {

    Maze2D m2;
    DefaultScene sc;
    Stage pStage;
    Button newGame, help, switchM;
    MazeGame c;
    Maze3D m3;
    Label direct;

    public MazeScene(DirectionListener k, Stage pStage, MazeGame c) {
        this.m3 = new Maze3D();
        this.pStage = pStage;
        this.c = c;
        direct = new Label("Direction: DOWN");
        m2 = new Maze2D();
        sc = new DefaultScene(pStage);
        newGame = new Button("new Game");
        help = new Button("?");
        switchM = new Button("2D/3D");
        newGame.setOnMouseClicked((Event event) -> {
            startNewGame();
        });

        switchM.setOnMouseClicked((Event event) -> {
            switchMs();
        });

        help.setOnMouseClicked((Event event) -> {
            help();
        });

        k.addKeyObserver(c);
        sc.addToMenuBar(help);
        sc.addToMenuBar(newGame);
        sc.addToMenuBar(switchM);
        sc.addToStatusBar(direct);
        sc.setonKeyPressed(k);
        sc.setCenter(m2.getPane());
        pStage.show();
    }

    private void startNewGame() {
        c.newGame();
    }

    @Override
    public void onPlayerPositionChange(Field player, Field[] surroundings) {
        m2.onPlayerPositionChange(player, surroundings);
        m3.onPlayerPositionChange(player, surroundings);
    }

    @Override
    public void newGame(int length, int heigth) {
        m2.newGame(length, heigth);
        m3.newGame(length, heigth);
    }

    private void help() {
        JOptionPane.showMessageDialog(null, "This is helpful!!!", "Help", JOptionPane.QUESTION_MESSAGE);
    }

    private void switchMs() {
        c.switchMode();
        if (c.isMode3d()) {
            sc.setCenter(m3.getPane());
        } else {
            sc.setCenter(m2.getPane());
        }
    }

    @Override
    public void onDirectionChange(Direction direction) {
        direct.setText("Direction: " + direction);
        m3.onDirectionChange(direction);
    }

    @Override
    public void run() {
        System.out.println("run");
    }

}
