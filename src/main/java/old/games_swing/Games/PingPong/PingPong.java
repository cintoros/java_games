package old.games_swing.Games.PingPong;

import old.games_swing.GameLogic.DefFrame;
import old.games_swing.GameLogic.ScoreCounter;
import old.games_swing.Sounds.Sounds;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

/**
 * The classic Ping Pong game
 *
 * @author Elias Schorr
 */
public class PingPong extends javax.swing.JPanel {

    private final Timer timerBall;//class objects
    private final Sounds ping;
    private final DefFrame parent;//gui objects
    private Direction direction = Direction.right;//variables
    private int angle = 0;
    private final ScoreCounter score;//to save the score of the game

    /**
     * creates an new Ping Pong game
     *
     * @param parent
     */
    public PingPong(DefFrame parent) {
        this.score = new ScoreCounter(2);
        initComponents();
        setFocusable(true);
        ping = new Sounds();
        timerBall = new Timer(25, new ActionListener() {//legt fest was der Timer macht
            @Override
            public void actionPerformed(ActionEvent evt) {
                bewegen();//bewegt den ball
                Punkte();//zählt die Punkte
                WinkelWand();//Ball wird an der Wand reflektiert
            }
        });
        parent.addToMenu(score.getchangingLabel());
        this.parent = parent;
    }

    private void bewegen() {//moves the ball in the direction and angle that are saved
        if (direction == Direction.right) {//if the direction is right move the ball to the riht
            Point p = ball.getLocation();
            p.x = p.x + 10;
            p.y = p.y + angle;
            ball.setLocation(p);
        } else {//else to the left
            Point p = ball.getLocation();
            p.x = p.x - 10;
            p.y = p.y + angle;
            ball.setLocation(p);
        }
        Point p = balkenrechts.getLocation();
        Point p2 = ball.getLocation();//if the ball touches the right bar set the direction left
        if (p.y + 100 >= p2.y && p2.y >= p.y - 10 && p2.x >= (this.getSize().width - 70)) {
            direction = Direction.left;
            ping.ping();//ping sound
            Interval();//setting of the interval
            int position = ((balkenrechts.getLocation().y + 100) - ball.getLocation().y);
            berechneWinkel(position);//setting of the angle
        }
        p = balkenlinks.getLocation();
        p2 = ball.getLocation();//if the ball touches the left bar set the direction right
        if ((p.y + 100) >= p2.y && p2.y >= (p.y - 10) && p2.x <= 30) {
            direction = Direction.right;
            ping.ping();//ping sound
            Interval();//setting of the interval
            int position = ((balkenlinks.getLocation().y + 100) - ball.getLocation().y);
            berechneWinkel(position);//setting of the angle
        }
    }

    private void Punkte() {//gives out the points and resets the ball if he is out of the field
        Point p = ball.getLocation();
        if (p.x <= 10) {
            System.out.println("Punkt für Spieler 2");
            score.addPoints(2, 1);
            Reset();
        }
        if (p.x >= (this.getSize().width - 40)) {
            System.out.println("Punkt für Spieler 1");
            score.addPoints(1, 1);
            Reset();
        }
    }

    private void Interval() {//setting of the interval
        if (timerBall.getDelay() > 15) {
            timerBall.setDelay(timerBall.getDelay() - 1);
        }
    }

    private void Reset() {//resetting all impoortant variables
        System.out.println("Ball zurück gesetzt");
        ball.setLocation(new Point(this.getSize().width / 2, this.getSize().height / 2));//ball is set into the center
        balkenlinks.setLocation(balkenlinks.getLocation().x, (getSize().height / 2) - 50);//bars are set into the center
        balkenrechts.setLocation(balkenrechts.getLocation().x, (getSize().height / 2) - 50);//bars are set into the center
        timerBall.setDelay(25);//interval is reseted
        angle = 0;//angle is reseted
        timerBall.stop();//Timer is stoped
    }

    private enum Direction {//saves the only possible directions of thee ball (left and right)

        right, left
    }

    private void berechneWinkel(int position) {//angle is calculated
        if (position <= 10) {
            angle += 5;
        } else if (position <= 20) {
            angle += 4;
        } else if (position <= 30) {
            angle += 3;
        } else if (position <= 40) {
            angle += 2;
        } else if (position <= 50) {
            angle += 1;
        } else if (position <= 60) {
            angle -= 1;
        } else if (position <= 70) {
            angle -= 2;
        } else if (position <= 80) {
            angle -= 3;
        } else if (position <= 90) {
            angle -= 4;
        } else {
            angle -= 5;
        }
    }

    private void WinkelWand() {//ball is rebounced ehen he hits an wall
        Point p = ball.getLocation(); 
        if (p.y >= (this.getSize().height - 20) || p.y <= 10) {
            angle = (-1) * angle;//ankle is reversed
            ping.ping();//sound is played
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ball = new javax.swing.JPanel();
        balkenlinks = new javax.swing.JPanel();
        balkenrechts = new javax.swing.JPanel();

        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1000, 600));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                ReSize(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout ballLayout = new javax.swing.GroupLayout(ball);
        ball.setLayout(ballLayout);
        ballLayout.setHorizontalGroup(
            ballLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        ballLayout.setVerticalGroup(
            ballLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        balkenlinks.setPreferredSize(new java.awt.Dimension(15, 100));
        balkenlinks.setRequestFocusEnabled(false);

        javax.swing.GroupLayout balkenlinksLayout = new javax.swing.GroupLayout(balkenlinks);
        balkenlinks.setLayout(balkenlinksLayout);
        balkenlinksLayout.setHorizontalGroup(
            balkenlinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        balkenlinksLayout.setVerticalGroup(
            balkenlinksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        balkenrechts.setPreferredSize(new java.awt.Dimension(15, 100));
        balkenrechts.setRequestFocusEnabled(false);

        javax.swing.GroupLayout balkenrechtsLayout = new javax.swing.GroupLayout(balkenrechts);
        balkenrechts.setLayout(balkenrechtsLayout);
        balkenrechtsLayout.setHorizontalGroup(
            balkenrechtsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        balkenrechtsLayout.setVerticalGroup(
            balkenrechtsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(balkenlinks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 950, Short.MAX_VALUE)
                .addComponent(balkenrechts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(495, 495, 495)
                    .addComponent(ball, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(495, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(balkenlinks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(balkenrechts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(111, 111, 111))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(295, 295, 295)
                    .addComponent(ball, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(295, Short.MAX_VALUE)))
        );

        getAccessibleContext().setAccessibleName("hkghk");
        getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents
    //method if an key is pressed
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        Point p;
        int Tastencode = evt.getKeyCode();
        switch (Tastencode) {
            case KeyEvent.VK_UP: {//Pfeiltaste nach oben
                p = balkenrechts.getLocation();
                p.y = p.y - 50;
                balkenrechts.setLocation(p);
                break;
            }
            case KeyEvent.VK_DOWN: {//Pfeiltaste nach unten
                p = balkenrechts.getLocation();
                p.y = p.y + 50;
                balkenrechts.setLocation(p);
                break;
            }
            case KeyEvent.VK_W: {//W-Taste
                p = balkenlinks.getLocation();
                p.y = p.y - 50;
                balkenlinks.setLocation(p);
                break;
            }
            case KeyEvent.VK_S: {//S-Taste
                p = balkenlinks.getLocation();
                p.y = p.y + 50;
                balkenlinks.setLocation(p);
                break;
            }
        }
    }//GEN-LAST:event_formKeyPressed
    //if the user clicks in the gamefield the timer is started
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        timerBall.start();
    }//GEN-LAST:event_formMousePressed
    //if the frame is resized the bars and ball are reseted
    private void ReSize(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_ReSize
        Reset();
    }//GEN-LAST:event_ReSize

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel balkenlinks;
    private javax.swing.JPanel balkenrechts;
    private javax.swing.JPanel ball;
    // End of variables declaration//GEN-END:variables
}
