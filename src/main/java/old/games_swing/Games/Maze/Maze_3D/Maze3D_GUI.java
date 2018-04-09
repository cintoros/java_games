package old.games_swing.Games.Maze.Maze_3D;

import old.games_swing.GameLogic.DefFrame;
import old.games_swing.KeybordListener.KeyObserver;
import old.games_swing.KeybordListener.KeyboardListener;
import old.games_swing.Games.Maze.MazeGame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * @author Elias
 * @school BBBaden
 * @created 05.09.2014
 * @version 1.0
 */
public class Maze3D_GUI implements KeyObserver {

    private int length, height, orientation, position[];
    private final String titel;
    private boolean[][] maze, surroundings;
    private final DrawPanel panel;
    private final KeyboardListener listener;
    private final DefFrame parent;
    private final MazeGame game = new MazeGame();
    private final JLabel direction;
    private final DefFrame frame;
    private final MazeMap map;
    private final JButton newGame, mapShow;

    public Maze3D_GUI() {
        this.mapShow = new JButton("show/hide Map");
        this.newGame = new JButton("new");
        this.frame = new DefFrame(200, 200, "3D Maze Map");
        frame.setVisible(false);
        map = new MazeMap(frame);
        this.direction = new JLabel("direction: UP");
        this.position = new int[2];
        this.orientation = 1;
        this.titel = "Maze-3D";
        this.parent = new DefFrame(1000, 1000, titel);
        parent.addToMenu(newGame);
        parent.addToMenu(mapShow);
        this.listener = new KeyboardListener();
        listener.addKeyObserver(this);
        parent.addKeyListener(listener);
        this.panel = new DrawPanel();
        panel.add(direction);
        parent.add(panel);
        surroundings = new boolean[3][3];
        newGame.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newGame();
            }
        });
        mapShow.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                map.visibleChange();
                parent.requestFocus();
            }
        });

        //starting a new Game
        newGame();
    }

    private void newGame() {
        int[] sizes = SizeAuswahlGUI.askNewSizes(10, 100, titel);
        height = sizes[0];
        length = sizes[1];
        game.newGame(sizes[0], sizes[1]);
        maze = game.getMaze();
        calculateSurroundings();
        stelleFeldDar();
        showMap();
    }

    private void showMap() {
        map.show(height, length, maze);
        parent.requestFocus();
    }

    private void surroundingsEmpty() {
        surroundings = new boolean[3][3];
    }

    private boolean getField(int x, int y) {
        try {
            return maze[y][x];
        } catch (Exception e) {
            return false;
        }
    }

    private void calculateSurroundings() {
        surroundingsEmpty();
        switch (orientation) {
            case 1: {//up
                int posX = position[0], posY = position[1];
                surroundings[0][0] = getField(posX - 1, posY);
                surroundings[1][0] = getField(posX - 1, posY - 1);
                surroundings[1][1] = getField(posX, posY - 1);
                surroundings[2][1] = getField(posX, posY - 2);
                surroundings[0][2] = getField(posX + 1, posY);
                surroundings[1][2] = getField(posX + 1, posY - 1);
                break;
            }
            case 2: {//right + escape route if the player has reached the 2nd last position
                int posX = position[0], posY = position[1];
                surroundings[0][0] = getField(posX, posY - 1);
                surroundings[1][0] = getField(posX + 1, posY - 1);
                surroundings[1][1] = getField(posX + 1, posY);
                surroundings[2][1] = getField(posX + 2, posY);
                surroundings[0][2] = getField(posX, posY + 1);
                surroundings[1][2] = getField(posX + 1, posY + 1);
                //end way
                if (posY == height - 1 && posX == length - 2) {
                    surroundings[2][1] = true;
                }
                if (posY == height - 1 && posX == length - 1) {
                    surroundings[2][1] = true;
                    surroundings[1][1] = true;
                }
                break;
            }
            case 3: {//down + escape route if the player has reached the 2nd last position
                int posX = position[0], posY = position[1];
                surroundings[0][0] = getField(posX + 1, posY);
                surroundings[1][0] = getField(posX + 1, posY + 1);
                surroundings[1][1] = getField(posX, posY + 1);
                surroundings[2][1] = getField(posX, posY + 2);
                surroundings[0][2] = getField(posX - 1, posY);
                surroundings[1][2] = getField(posX - 1, posY + 1);
                break;
            }
            case 4: {//left 
                int posX = position[0], posY = position[1];
                surroundings[0][0] = getField(posX, posY + 1);
                surroundings[1][0] = getField(posX - 1, posY + 1);
                surroundings[1][1] = getField(posX - 1, posY);
                surroundings[2][1] = getField(posX - 2, posY);
                surroundings[0][2] = getField(posX, posY - 1);
                surroundings[1][2] = getField(posX - 1, posY - 1);
                //end way
                if (posY == height - 2 && posX == length - 1) {
                    surroundings[2][1] = true;
                }
                if (posY == height - 1 && posX == length - 1) {
                    surroundings[2][1] = true;
                    surroundings[1][1] = true;
                }
                break;
            }
        }
    }

    private void stelleFeldDar() {
        panel.setSurroundings(surroundings);
        switch (orientation) {
            case 1: {//up
                direction.setText("direction: Up");
                break;
            }
            case 2: {//up
                direction.setText("direction: Right");
                break;
            }
            case 3: {//up
                direction.setText("direction: Down");
                break;
            }
            case 4: {//up
                direction.setText("direction: Left");
                break;
            }
        }

    }

    /**
     * changes the position Integer to an new position
     *
     * @param change the value that the position is changed.
     */
    private void orientationChanged(int change) {
        orientation += change;
        if (orientation > 4) {
            orientation = orientation - 4;
        }
    }

    @Override
    public void onStateChange(KeyboardListener.ArrowDirection direction) {
        if (!game.getEnde()) {
            switch (direction) {
                case Down: {
                    orientationChanged(2);
                    break;
                }
                case Left: {
                    orientationChanged(3);
                    break;
                }
                case Right: {
                    orientationChanged(1);
                    break;
                }
                case Up: {
                    switch (orientation) {
                        case 1: {
                            game.move(KeyboardListener.ArrowDirection.Up);
                            break;
                        }
                        case 2: {
                            game.move(KeyboardListener.ArrowDirection.Right);
                            break;
                        }
                        case 3: {
                            game.move(KeyboardListener.ArrowDirection.Down);
                            break;
                        }
                        case 4: {
                            game.move(KeyboardListener.ArrowDirection.Left);
                            break;
                        }
                    }
                    position = game.getPlayerPosition();
                    break;
                }
            }
            calculateSurroundings();
            stelleFeldDar();
        } else {
            JOptionPane.showMessageDialog(null, "Ende erreicht Bravo!!!", titel, JOptionPane.WARNING_MESSAGE);
        }
    }
}
