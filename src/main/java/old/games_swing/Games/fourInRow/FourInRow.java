package old.games_swing.Games.fourInRow;

import old.games_swing.GameLogic.DefFrame;
import old.games_swing.KeybordListener.KeyObserver;
import old.games_swing.KeybordListener.KeyboardListener;
import old.games_swing.KeybordListener.KeyboardListener.ArrowDirection;
import old.games_swing.GameLogic.ScoreCounter;
import old.games_swing.Sounds.Sounds;
import old.games_swing.GameLogic.TimeCalculation;
import old.games_swing.Games.fourInRow.Stone.StoneColor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * the gui of the classic game in wchich you drob stones and try to have 4
 * stones in a row or diagonal
 *
 * @author Elias
 */
public class FourInRow implements KeyObserver {

    private final JPanel background, backgroundDown, backgroundUp;//containers for the stones
    private Stone[][] stonesDown;//the stones which are placed in the containers
    private JButton[] stonesUp;
    private final ScoreCounter score;//saves the score of the players
    private final TimeCalculation time;//gives out how long the game is started
    private final DefFrame frame;//the default frame in which this game is opended
    private final JButton reset;//button to reset the game
    private final FourInRowGame game;//the game logical class
    private int position; //saves the position from witch the stone is droped
    private StoneColor player;//saves which player can move
    private final Sounds sound;//gives out sounds
    private final KeyboardListener listener;

    /**
     * creates an new game GUI
     *
     * @param importFrame
     */
    public FourInRow(DefFrame importFrame) {
        this.reset = new JButton("neu");
        importFrame.addToMenu(reset);
        score = new ScoreCounter(2);
        importFrame.addToMenu(score.getchangingLabel());
        time = new TimeCalculation();
        importFrame.addToMenu(time.getChanginglabel());
        time.start();
        this.sound = new Sounds();
        this.player = StoneColor.Red;
        this.position = 0;
        this.game = new FourInRowGame(this);
        background = new JPanel(new BorderLayout());
        backgroundUp = new JPanel(new GridLayout(1, 7));
        backgroundUp.setBackground(Color.gray);
        backgroundDown = new JPanel(new GridLayout(6, 7));
        backgroundDown.setBackground(Color.blue);
        background.add(backgroundUp, BorderLayout.NORTH);
        background.add(backgroundDown, BorderLayout.CENTER);
        creatingFields();
        importFrame.add(background);
        listener = new KeyboardListener();
        listener.addKeyObserver(this);
        importFrame.addKeyListener(listener);
        reset.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reset();
            }
        });
        frame = importFrame;
        frame.requestFocus();
    }

    private void playerWon() {//looks if the player has won
        if (game.hasWon(player)) {
            String spieler;
            if (player == StoneColor.Red) {
                score.addPoints(1, 1);
                spieler = "Spieler Rot";
            } else {
                score.addPoints(2, 1);
                spieler = "Spieler Gelb";
            }
            sound.winBig();
            JOptionPane.showMessageDialog(null, spieler + " hat gewonnen!", "4 gewinnt", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void creatingFields() {//creates the stones
        stonesUp = new JButton[7];
        stonesDown = new Stone[6][7];
        for (int xIndex = 0; xIndex < 7; xIndex++) {
            stonesUp[xIndex] = new JButton();
            stonesUp[xIndex].setBackground(Color.white);
            stonesUp[xIndex].setEnabled(false);
            backgroundUp.add(stonesUp[xIndex]);
        }
        for (int yIndex = 0; yIndex < 6; yIndex++) {
            for (int xIndex = 0; xIndex < 7; xIndex++) {
                stonesDown[yIndex][xIndex] = new Stone();
                backgroundDown.add(stonesDown[yIndex][xIndex]);
            }
        }
        position = 0;
        stonesUp[0].setBackground(Color.red);
    }

    /**
     * resets the game stones
     */
    public void reset() {
        game.reset();
        for (int yIndex = 0; yIndex < 6; yIndex++) {
            for (int xIndex = 0; xIndex < 7; xIndex++) {
                redrawStone(yIndex, xIndex, StoneColor.White);
            }
        }
        frame.requestFocus();
    }

    /**
     * redraws the stone at the x /y K^cordinates with the color of the player
     *
     * @param xIndex
     * @param yIndex
     * @param player
     */
    public void redrawStone(int xIndex, int yIndex, StoneColor player) {
        stonesDown[xIndex][yIndex].drawStone(player);
        background.repaint();
//        try{
//            Thread.sleep(100);
//        }catch(Exception ex){
//        }
    }

    @Override
    public void onStateChange(ArrowDirection direction) {
        if (!game.isOver()) {
            boolean resultat = false;
            switch (direction) {
                case Down: {
                    resultat = game.setStone(position, player);//saves the resultat of the function 
                    break;
                }
                case Left: {
                    if (position > 0) {
                        stonesUp[position].setBackground(Color.white);
                        position--;
                        if (player == StoneColor.Red) {
                            stonesUp[position].setBackground(Color.red);
                        } else {
                            stonesUp[position].setBackground(Color.yellow);
                        }
                    } else {
                        sound.lose();//if not possible give out a fail sound
                    }
                    break;
                }
                case Right: {
                    if (position < 6) {
                        stonesUp[position].setBackground(Color.white);
                        position++;
                        if (player == StoneColor.Red) {
                            stonesUp[position].setBackground(Color.red);
                        } else {
                            stonesUp[position].setBackground(Color.yellow);
                        }
                    } else {
                        sound.lose();//if not possible give out a fail sound
                    }
                    break;
                }
            }
            playerWon();//looks if the player has won
            if (resultat) {//if the functions was successfull
                if (player == StoneColor.Red) {//change the player
                    player = StoneColor.Yellow;
                    stonesUp[position].setBackground(Color.yellow);
                } else {
                    player = StoneColor.Red;
                    stonesUp[position].setBackground(Color.red);
                }
            } else {
                sound.lose();//else give a fail sound 
            }
        }else{
            sound.lose();
        }
    }
}
