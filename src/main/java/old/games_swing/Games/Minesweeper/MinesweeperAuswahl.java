package old.games_swing.Games.Minesweeper;

/**
 * @author Elias Schorr
 */
public class MinesweeperAuswahl extends javax.swing.JFrame {

    Minesweeper parentDeluxe;

    /**
     * Creates new form MinesweeperAuswahl
     *
     * @param parent
     */
    public MinesweeperAuswahl(Minesweeper parent) {
        this.parentDeluxe = parent;
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void maxBombsCalculation() {//sets the max value of the  bombSlider depending on the other sliders
        int a, b;
        a = lenghtSlider.getValue();
        b = heightSlider.getValue();
        if ((a * b) / 2 < 150) {
            bombSlider.setMaximum((a * b) / 2);
        } else {
            bombSlider.setMaximum(150);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLänge = new javax.swing.JLabel();
        lblHöhe = new javax.swing.JLabel();
        lblBombe = new javax.swing.JLabel();
        lblBeschreibung = new javax.swing.JLabel();
        lenghtSlider = new javax.swing.JSlider();
        lblLängeAusgabe = new javax.swing.JLabel();
        lblHöhenAusgabe = new javax.swing.JLabel();
        lblBombenAusgabe = new javax.swing.JLabel();
        cmdNew = new javax.swing.JButton();
        heightSlider = new javax.swing.JSlider();
        bombSlider = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("MinesweeperAuswahl"); // NOI18N

        lblLänge.setText("Länge");
        lblLänge.setToolTipText("");
        lblLänge.setName(""); // NOI18N

        lblHöhe.setText("Höhe");

        lblBombe.setText("Bomben");

        lblBeschreibung.setText("<html>  <p>Ziel des Spieles ist; alle Felder ohne Bomben aufzudecken. </p>  <p>Die Boben-Felder dürfen dabei nicht gedrückt  werden oder das Spiel ist verloren. </p>  <p>Falls das Spiel verloren oder gewonnen ist kann man entweder;</p>  <p>per Reset die Kacheln wieder verdecken oder </p>  <p>mit Neu ein neues Spielfeld berechnen lassen.</p>");
        lblBeschreibung.setToolTipText("");

        lenghtSlider.setMaximum(30);
        lenghtSlider.setMinimum(5);
        lenghtSlider.setPaintTicks(true);
        lenghtSlider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                LängenChange(evt);
            }
        });

        lblLängeAusgabe.setText("30");

        lblHöhenAusgabe.setText("30");

        lblBombenAusgabe.setText("150");

        cmdNew.setText("Neu");
        cmdNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNewActionPerformed(evt);
            }
        });

        heightSlider.setMaximum(30);
        heightSlider.setMinimum(5);
        heightSlider.setPaintTicks(true);
        heightSlider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                changedHeight(evt);
            }
        });

        bombSlider.setMaximum(150);
        bombSlider.setMinimum(5);
        bombSlider.setPaintTicks(true);
        bombSlider.setValue(150);
        bombSlider.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                changedBombs(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLänge)
                            .addComponent(lblHöhe)
                            .addComponent(lblBombe))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bombSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblBombenAusgabe))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lenghtSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblLängeAusgabe))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(heightSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblHöhenAusgabe, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(cmdNew)
                    .addComponent(lblBeschreibung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLänge)
                    .addComponent(lenghtSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLängeAusgabe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHöhe)
                    .addComponent(heightSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHöhenAusgabe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBombe)
                    .addComponent(bombSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBombenAusgabe))
                .addGap(18, 18, 18)
                .addComponent(cmdNew)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblBeschreibung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //methdo called if the new Button is pressed
    private void cmdNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNewActionPerformed
        int lenght = lenghtSlider.getValue();
        int height = heightSlider.getValue();
        int bombs = bombSlider.getValue();
        parentDeluxe.creatingGameField(height, lenght, bombs);
        this.dispose();
    }//GEN-LAST:event_cmdNewActionPerformed

    private void LängenChange(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LängenChange
        int a;
        String b;
        maxBombsCalculation();
        a = lenghtSlider.getValue();
        lblLängeAusgabe.setText("" + lenghtSlider.getValue() + "");
        changedBombs(evt);
    }//GEN-LAST:event_LängenChange

    private void changedHeight(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changedHeight
        maxBombsCalculation();
        lblHöhenAusgabe.setText("" + heightSlider.getValue() + "");
        changedBombs(evt);
    }//GEN-LAST:event_changedHeight

    private void changedBombs(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changedBombs
        lblBombenAusgabe.setText("" + bombSlider.getValue() + "");
    }//GEN-LAST:event_changedBombs

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider bombSlider;
    private javax.swing.JButton cmdNew;
    private javax.swing.JSlider heightSlider;
    private javax.swing.JLabel lblBeschreibung;
    private javax.swing.JLabel lblBombe;
    private javax.swing.JLabel lblBombenAusgabe;
    private javax.swing.JLabel lblHöhe;
    private javax.swing.JLabel lblHöhenAusgabe;
    private javax.swing.JLabel lblLänge;
    private javax.swing.JLabel lblLängeAusgabe;
    private javax.swing.JSlider lenghtSlider;
    // End of variables declaration//GEN-END:variables
}
