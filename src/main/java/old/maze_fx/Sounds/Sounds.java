package old.maze_fx.Sounds;

import javafx.scene.media.AudioClip;

/**
 * This class is for playing diffrent kind of sounds. when the methods are first
 * called the AudioClip is loaded and played after that. Second thime the Clips
 * are only played
 *
 * @author Elias
 */
public class Sounds {

    private final AudioClip win, lose;

    /**
     * loading of all sounds
     */
    public Sounds() {
        win = new AudioClip(ClassLoader.getSystemResource("tada.wav").toString());
        win.setCycleCount(10);
        lose = new AudioClip(ClassLoader.getSystemResource("stop.wav").toString());
    }

    /**
     * plays an win sound
     */
    public void win() {
        try {
            win.play(1.0);
        } catch (Exception e) {
        }
    }

    /**
     * plays a lose sound
     */
    public void lose() {
        try {
            lose.play(1.0);
        } catch (Exception e) {
        }
    }
}
