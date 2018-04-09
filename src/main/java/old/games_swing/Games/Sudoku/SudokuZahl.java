package old.games_swing.Games.Sudoku;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author Elias
 */
public class SudokuZahl extends JButton {

    private int value;//variable to save the number in it
    private final int maxValue;//variable to save the max possible number in it.
    private final boolean enabled;//variable if the number in this field can be changed.

    /**
     * creates an new Sudokufield
     *
     * @param valueImport the value at the old.maze_fx.start
     * @param enabledImport boolean if the field can be changed or not
     * @param maxImport the maximal value that can be reached
     */
    public SudokuZahl(int valueImport, boolean enabledImport, int maxImport) {
        this.value = valueImport;
        this.maxValue = maxImport;
        this.enabled = enabledImport;
        this.setBackground(Color.white);
        if (!enabledImport) {
            this.setForeground(Color.blue);
            this.setText("" + value);
        }
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickChanged();
            }
        });
    }

    private void clickChanged() {
        if (enabled) {
            this.setForeground(Color.black);
            if (value == maxValue) {
                value = 0;
            }
            value++;
            this.setText("" + value);
        }
    }

    /**
     * chanches the color if the value according to if the value was true or not
     *
     * @param wasTrue boolean if the given Number was true
     */
    public void setNumberWasTrue(boolean wasTrue) {
        if (enabled) {
            if (wasTrue) {
                this.setForeground(Color.green);
            } else {
                this.setForeground(Color.red);
            }
        }
    }

    /**
     * @return gives back the actual Number of the Field
     */
    public int getNumber() {
        return value;
    }
}
