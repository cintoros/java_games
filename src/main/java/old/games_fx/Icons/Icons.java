package old.games_fx.Icons;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * @author Elias Schorr, BBBaden
 * @since 16.09.2014
 * @version 1.0
 */
public class Icons {

    /**
     * gives back the icon as Image
     *
     * @return the icon as Image
     */
    public static Image getBF3_Image() {
        try {
            Image icon = new ImageIcon("battlefield.png").getImage();
            return icon;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * gives back the icon as ImageIcon
     *
     * @return the icon as ImageIcon
     */
    public static ImageIcon getBF3_Icon() {
        try {
            ImageIcon icon = new ImageIcon("battlefield.png");
            return icon;
        } catch (Exception e) {
            return null;
        }
    }
}
