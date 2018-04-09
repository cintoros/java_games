package old.games_swing.Games.Memory;

import old.games_swing.GameLogic.DefFrame;
import old.games_swing.GameLogic.ScoreCounter;
import old.games_swing.Sounds.Sounds;
import old.games_swing.GameLogic.TimeCalculation;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Memory 2 cards that have the same Color/text/image are a pair the player has
 * to find all pairs over the gmaedfield
 *
 * @author Elias
 */
public class Memory {

    private final DefFrame parent;//classes
    private final MemoryBerechnung berechnung;
    private final Sounds sounds;
    private final TimeCalculation zeit;
    private final ScoreCounter rmPairs;//gui
    private JButton newGame, sender1, sender2, sender3;
    private JButton[] memory;
    private JPanel background;
    private ImageIcon images[];//varaibles
    private Color[] farben;
    private int[] places, zahlen;
    private int cards, send1Zahl;

    /**
     * creates an new Memory Object and starts the timer
     *
     * @param parent
     */
    public Memory(DefFrame parent) {
        cards = 0;
        sounds = new Sounds();
        newGame = new JButton("neues Spiel");
        rmPairs = new ScoreCounter("verbleibende Paare: ", 0);
        berechnung = new MemoryBerechnung();
        newGame = new JButton("neues Spiel");
        newGame.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newGame();
            }
        });
        parent.addToMenu(newGame);
        parent.addToMenu(rmPairs.getchangingLabel());
        zeit = new TimeCalculation();
        parent.addToMenu(zeit.getChanginglabel());
        this.parent = parent;
        zeit.start();
    }

    /**
     * starts a new Memory Game/ asks the player, how much memorys he wants
     */
    public void newGame() {
        Object[] options = {"8", "16", "32", "50", "72", "abbrechen"};
        Object[] options2 = {"Farben", "Bilder", "Zahlen", "abbrechen"};
        int length = 0;
        boolean abort = false;
        int inputInt = JOptionPane.showOptionDialog(null, "Wie viele Paare sollen es sein? ", "Memory", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        int pair = 0;
        switch (inputInt) {
            case 0: {
                pair = 8;
                length = 4;
                break;
            }
            case 1: {
                pair = 18;
                length = 6;
                break;
            }
            case 2: {
                pair = 32;
                length = 8;
                break;
            }
            case 3: {
                pair = 50;
                length = 10;
                break;
            }
            case 4: {
                pair = 72;
                length = 12;
                break;
            }
            default: {
                abort = true;
                break;
            }
        }
        if (!abort) {
            inputInt = JOptionPane.showOptionDialog(null, "Sollen es Bilder,Zahlen oder Farben sein? ", "Memory", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options2, options2[0]);

            switch (inputInt) {
                case 0: {
                    break;
                }
                case 1: {
                    break;
                }
                case 2: {
                    break;
                }
                default: {
                    abort = true;
                    break;
                }
            }
            if (!abort) {
                cards = length * length;
                rmPairs.setScore(1, pair);
                fieldDef(length, inputInt, pair);
            }
        }
    }

    private void fieldDef(int length, int input, int pair) {
        try {
            parent.remove(background);
        } catch (Exception ex) {
        }
        background = new JPanel(new GridLayout(length, length));
        memory = new JButton[cards];
        places = berechnung.calculateMemory(pair);//Fehler
        if (input == 1) {
            images = berechnung.getImages(pair);
            for (int i = 0; i < cards; i++) {
                fieldIn(i);
                memory[i].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        JButton bu = (JButton) evt.getSource();
                        if (bu.getIcon() == null) {
                            int i = Integer.parseInt(bu.getName());
                            bu.setIcon(images[places[i]]);
                            memoryEqual(bu, i);
                        }
                    }
                });
            }
        } else if (input == 0) {
            farben = berechnung.getColors(pair);
            for (int i = 0; i < cards; i++) {
                fieldIn(i);
                memory[i].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        JButton bu = (JButton) evt.getSource();
                        if (bu.getBackground() == Color.white) {
                            int i = Integer.parseInt(bu.getName());
                            bu.setBackground(farben[places[i]]);
                            memoryEqual(bu, i);
                        }
                    }
                });
            }
        } else {
            zahlen = berechnung.getZahlen(pair);
            for (int i = 0; i < cards; i++) {
                fieldIn(i);
                memory[i].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        JButton bu = (JButton) evt.getSource();
                        if (bu.getText().equals("")) {
                            int i = Integer.parseInt(bu.getName());
                            bu.setText("" + zahlen[places[i]]);
                            memoryEqual(bu, i);
                        }
                    }
                });
            }
        }
        parent.add(background);
        parent.pack();
    }

    private void fieldIn(int i) {
        memory[i] = new JButton();
        background.add(memory[i]);
        memory[i].setBackground(Color.white);
        memory[i].setIcon(null);
        memory[i].setText("");
        memory[i].setName(i + "");
        memory[i].setBorder(BorderFactory.createRaisedBevelBorder());

    }

    private void memoryEqual(JButton sender, int i) {//looks if twoo equal memory cards are selected if yes remove it form the list
        if (sender1 == null) {
            sender1 = sender;
            send1Zahl = i;
            try {
                sender2.setText("");
                sender2.setBackground(Color.white);
                sender2.setIcon(null);
                this.sender3.setText("");
                this.sender3.setBackground(Color.white);
                this.sender3.setIcon(null);
                sender2 = null;
                this.sender3 = null;
            } catch (Exception ex) {
            }
        } else {
            if (sender1 != sender) {
                if (places[send1Zahl] == places[i]) {
                    sender1.setEnabled(false);
                    sender.setEnabled(false);
                    sounds.winLittle();
                    win();
                    sender2 = null;
                    this.sender3 = null;
                } else {
                    sender2 = sender1;
                    this.sender3 = sender;
                    sounds.lose();
                    sender1.setEnabled(true);
                    sender.setEnabled(true);
                }
                sender1 = null;
                send1Zahl = -1;
            }
        }
    }

    private void win() {
        rmPairs.removePoints(1, 1);//one pair less to find
        sounds.winLittle();
        if (rmPairs.getPointsof(1) == 0) {
            sounds.winBig();
            JOptionPane.showMessageDialog(null, "gewonnen!!!!!.", "Memory", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
