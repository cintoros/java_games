package maze_fx;

import javafx.scene.media.AudioClip;

public class Sounds {

    private final AudioClip win, lose;

    public Sounds() {
        win = new AudioClip(ClassLoader.getSystemResource("tada.wav")
                .toString());
        win.setCycleCount(10);
        lose = new AudioClip(ClassLoader.getSystemResource("stop.wav")
                .toString());
    }

    public void win() {
        try {
            win.play(1.0);
        } catch (Exception e) {
        }
    }

    public void lose() {
        try {
            lose.play(1.0);
        } catch (Exception e) {
        }
    }
}
