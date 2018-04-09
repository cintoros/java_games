package old.games_swing.Games.Minesweeper;

import old.games_swing.GameLogic.DefFrame;
import old.games_swing.GameLogic.ScoreCounter;
import old.games_swing.Sounds.Sounds;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import old.games_swing.GameLogic.TimeCalculation;

/**
 * the Class of Minesweeper can be loaded in Frame or in an Applet
 *
 * @author Elias
 */
public class Minesweeper {

    Sounds sound;//sound object
    ScoreCounter bombs, flags;
    private JPanel backGround;//gui objects
    private final DefFrame frame;
    private JButton newGame, resetGame, sender, fields[][];
    private int staticIndex, length, height, victory, bombList[][];//variables
    private boolean firstStart = false, ende = false;
    private final TimeCalculation timer;

    /**
     * Creates new MinesweeperDeluxe for a Frame
     *
     * @param parentFrame
     */
    public Minesweeper(DefFrame parentFrame) {
        this.frame = parentFrame;
        frame.setResizable(true);
        timer = new TimeCalculation();
        initObjects();
        settingListeners();
        New();
    }

    private void initObjects() {//sets the Objects
        sound = new Sounds();
        bombs = new ScoreCounter("Bomben: ", 0);
        flags = new ScoreCounter("Flaggen: ", 0);
        //initialising of Objects
        backGround = new JPanel();
        newGame = new JButton("neues Spiel");
        resetGame = new JButton("Reset");
        frame.addToMenu(newGame);
        frame.addToMenu(resetGame);
        frame.addToMenu(bombs.getchangingLabel());
        frame.addToMenu(flags.getchangingLabel());
        frame.addToMenu(timer.getChanginglabel());
        backGround.setBackground(Color.lightGray);
    }

    private void settingListeners() {//sets the Listeners
        newGame.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                New();
            }
        });
        resetGame.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Reset(evt);
            }
        });
    }

    /**
     * the game is created all buttons, mines and so is set.
     *
     * @param height
     * @param length
     * @param bomben
     */
    public void creatingGameField(int height, int length, int bomben) {
        bombs.setScore(1, bomben);
        flags.setScore(1, 0);
        frame.setSize(length * 25, height * 25);
        frame.remove(backGround);
        backGround = new JPanel(new GridLayout(height, length));
        frame.add(backGround);
        if (firstStart == false) {//if it isn't the first time you run teh game the existing varaibles have to be reset
            for (int index = 0; index < this.length; index++) {
                for (int i = 0; i < this.height; i++) {
                    try {
                        backGround.remove(fields[index][i]);
                        fields[index][i] = null;
                    } catch (Exception ex) {
                    }
                }
            }
        } else {//else it saves that this was your first old.maze_fx.start
            firstStart = false;
        }//declaration of other varaibles
        firstStart = false;
        ende = false;
        this.length = height;
        this.height = length;
        staticIndex = (height * length) - 1;
        victory = (staticIndex - bomben) + 1;
        bombList = new int[height][length];
        fields = new JButton[height][length];
        for (int index = 0; index < height; index++) {
            for (int i = 0; i < length; i++) {
                bombList[index][i] = 0;
            }
        }//calling of the methods responsibel for creating a new field of mines
        MinesweeperBerechnung berechnung = new MinesweeperBerechnung();
        creatingFields();
        bombList = berechnung.calculatingBombs(height, length, bomben);
        settingActions();
        timer.reset();
        timer.start();
    }

    private void creatingFields() {//the buttons are created and they are given values and listeners
        for (int index = 0; index < length; index++) {
            for (int i = 0; i < height; i++) {
                fields[index][i] = new JButton();
                backGround.add(fields[index][i]);
                fields[index][i].setText("");
                fields[index][i].setBorder(BorderFactory.createRaisedBevelBorder());
                fields[index][i].setVisible(true);
                fields[index][i].setBackground(Color.white);
                fields[index][i].setName("X" + index + "Y" + i);
                fields[index][i].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        if (evt.getButton() == 3) {
                            setFlag(evt);
                        }
                    }
                });
            }
        }
    }

    private void settingActions() {//looks at every Object
        for (int index = 0; index < length; index++) {
            for (int i = 0; i < height; i++) {
                if (bombList[index][i] == 0) {//if there are no bombs around...
                    // make this
                    fields[index][i].addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            if (evt.getButton() == 1) {//only if it is left clicked
                                noBombs(evt);
                            }
                        }
                    });
                } else if (bombList[index][i] >= 9) {
                    fields[index][i].addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            if (evt.getButton() == 1) {
                                kaboomBomb(evt);
                            }
                        }
                    });
                } else {//if it has boms around and it itsself isn't one...
                    // make this
                    fields[index][i].addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            if (evt.getButton() == 1) {
                                someBombs(evt);
                            }
                        }
                    });
                }
            }
        }
    }

    private void victory() {
        victory = victory - 1;//every timer this methdo is called it saves the new steps required for winning
        if (victory == 0) {//if the player wins he's informed of it and the game is over.
            sound.winLittle();
            timer.stop();
            JOptionPane.showMessageDialog(null, "Sie haben das Spiel in " + timer.getChanginglabel().getText() + " geschaft\nUm ein neues Spiel zu beginnen klicken sie auf <neues Spiel> ", "Minesweeper", JOptionPane.OK_CANCEL_OPTION);
            ende = true;
        }
    }

    private void kaboomBomb(java.awt.event.MouseEvent evt) {
        //method called from bombs
        sender = (JButton) evt.getComponent();
        if (!ende) {//if the game isn't over jet
            if (sender.getText().equals("")) {//the button is set as a bomb and the player is informed that the game is over.
                sender.setBackground(Color.red);
                sender.setText("B");
                sound.boom();
                JOptionPane.showMessageDialog(null, "Sie haben verloren.\nUm ein neues Spiel zu beginnen klicken sie auf <neues Spiel>\nUm es noch einmal zu versuchen drücken sie auf <Reset>", "Minesweeper", JOptionPane.OK_CANCEL_OPTION);
                ende = true;
            }
        }
    }

    private void setFlag(java.awt.event.MouseEvent evt) {
        sender = (JButton) evt.getComponent();
        if (!ende) {//if the game isn't over jet
            switch (sender.getText()) {//set or remove an Flag
                case "":
                    sender.setBackground(Color.magenta);
                    sender.setText("?");
                    flags.addPoints(1, 1);
                    break;
                case "?":
                    sender.setBackground(Color.white);
                    sender.setText("");
                    flags.removePoints(1, 1);
                    break;
            }
        }
    }

    private void noBombs(java.awt.event.MouseEvent evt) {
        sender = (JButton) evt.getComponent();
        if (!ende) {//if the game isn't over jet
            if (sender.getText().equals("")) {//if the field is empty
                sender.setBackground(Color.green);//it is changed and the victory is one step closer
                sender.setText("X");
                victory();
                autoSolve(sender);
            }
        }
    }

    private void someBombs(java.awt.event.MouseEvent evt) {
        sender = (JButton) evt.getComponent();
        if (!ende) {//if the game isn't over jet
            if (sender.getText().equals("")) {//if the field is empty
                int index = 0, i = 0;//gives back the postition of the field in the array
                boolean gefunden = false;
                for (index = 0; index < length; index++) {
                    for (i = 0; i < height; i++) {
                        if (compareField(fields[index][i], sender)) {
                            gefunden = true;
                            break;
                        }
                    }
                    if (gefunden) {
                        break;
                    }
                }
                sender.setBackground(Color.orange);
                sender.setText(bombList[index][i] + "");
                victory();//it is changed andthe victory is one step closer
            }
        }
    }

    private void New() {//opening of the creation window for an new Game
        old.games_swing.Games.Minesweeper.MinesweeperAuswahl M1 = new old.games_swing.Games.Minesweeper.MinesweeperAuswahl(this);
        M1.setVisible(true);
    }

    private void Reset(java.awt.event.MouseEvent evt) {//the fields are reseted
        for (int index = 0; index < length; index++) {
            for (int i = 0; i < height; i++) {
                fields[index][i].setText("");
                fields[index][i].setBackground(Color.white);
            }
        }//varaibles are reseted
        victory = (staticIndex - bombs.getPointsof(1)) + 1;
        ende = false;
        flags.setScore(1, 0);
    }

    private boolean compareField(JButton field, JButton sender) {//returns if an feld is equal to another
        return field.getName().equals(sender.getName());
    }

    private void autoSolve(JButton sender) {//dispatches all Buttons aound this one if they aren't already dipatched.
        try {
            int index = 0, i = 0;
            boolean gefunden = false;
            for (index = 0; index < length; index++) {
                for (i = 0; i < height; i++) {
                    if (compareField(fields[index][i], sender)) {
                        gefunden = true;
                        break;
                    }
                }
                if (gefunden) {
                    break;
                }
            }
            if (index == 0) {
                fields[index + 1][i].dispatchEvent(new MouseEvent(fields[index + 1][i], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                if (i == 0) {
                    fields[index + 1][i + 1].dispatchEvent(new MouseEvent(fields[index + 1][i + 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                    fields[index][i + 1].dispatchEvent(new MouseEvent(fields[index][i + 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                } else {
                    fields[index + 1][i - 1].dispatchEvent(new MouseEvent(fields[index + 1][i - 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                    fields[index][i - 1].dispatchEvent(new MouseEvent(fields[index][i - 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));

                    if (i != height - 1) {
                        fields[index + 1][i + 1].dispatchEvent(new MouseEvent(fields[index + 1][i + 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                        fields[index][i + 1].dispatchEvent(new MouseEvent(fields[index][i + 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                    }
                }
            } else {
                fields[index - 1][i].dispatchEvent(new MouseEvent(fields[index - 1][i], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                if (i == 0) {
                    fields[index - 1][i + 1].dispatchEvent(new MouseEvent(fields[index - 1][i + 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                    fields[index][i + 1].dispatchEvent(new MouseEvent(fields[index][i + 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                } else {
                    fields[index - 1][i - 1].dispatchEvent(new MouseEvent(fields[index - 1][i - 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                    fields[index][i - 1].dispatchEvent(new MouseEvent(fields[index][i - 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));

                    if (i != height - 1) {
                        fields[index - 1][i + 1].dispatchEvent(new MouseEvent(fields[index - 1][i + 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                        fields[index][i + 1].dispatchEvent(new MouseEvent(fields[index][i + 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                    }
                }
                if (index != length - 1) {
                    if (i == 0) {
                        fields[index + 1][i + 1].dispatchEvent(new MouseEvent(fields[index + 1][i + 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                        fields[index][i + 1].dispatchEvent(new MouseEvent(fields[index][i + 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                    } else {
                        fields[index + 1][i - 1].dispatchEvent(new MouseEvent(fields[index + 1][i - 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                        fields[index][i - 1].dispatchEvent(new MouseEvent(fields[index][i - 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));

                        if (i != height - 1) {
                            fields[index + 1][i + 1].dispatchEvent(new MouseEvent(fields[index + 1][i + 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                            fields[index][i + 1].dispatchEvent(new MouseEvent(fields[index][i + 1], MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, 0, 0, 0, false, 1));
                        }
                    }
                }
            }
        } catch (StackOverflowError ex) {
            System.out.println("zu wenig Bomben!!!!!!!!");
        }
    }
}
