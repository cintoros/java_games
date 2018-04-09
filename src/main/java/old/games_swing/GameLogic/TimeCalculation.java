package old.games_swing.GameLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * This Class is for calculating/saving the time a Programm is started and
 * giving it back on a JLabel.
 *
 * @author Elias
 */
public class TimeCalculation {

    private final Timer timeCounter;
    private int seconds, minutes, hours;
    private final int startSeconds, startMinutes, startHours;
    private final JLabel time;

    /**
     * new Timer is created that siôunts the time
     */
    public TimeCalculation() {
        time = new JLabel();
        startHours = 0;
        startMinutes = 0;
        startSeconds = 0;
        reset();
        timeCounter = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                if (seconds >= 60) {
                    seconds = 0;
                    minutes++;
                    if (minutes >= 60) {
                        minutes = 0;
                        hours++;
                    }
                }
                time.setText("Zeit: " + hours + ":" + minutes + ":" + seconds);
            }
        });
    }

    /**
     * timeer is created that runs backwards needs an old.maze_fx.start time stops
     * automatically when the time limit is up
     *
     * @param importHours
     * @param importMinutes
     * @param importSeconds
     */
    public TimeCalculation(int importHours, int importMinutes, int importSeconds) {
        time = new JLabel();
        startHours = importHours;
        startMinutes = importMinutes;
        startSeconds = importSeconds;
        reset();
        timeCounter = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds--;
                if (seconds < 0) {
                    seconds = 59;
                    minutes--;
                    if (minutes < 0) {
                        minutes = 59;
                        hours--;
                    }
                }
                time.setText("Zeit: " + hours + ":" + minutes + ":" + seconds);
                if (seconds <= 0 && minutes <= 0 && hours <= 0) {
                    timeCounter.stop();
                }
            }
        });
    }

    /**
     * starts or continues the time
     */
    public void start() {
        try {
            timeCounter.start();
        } catch (Exception ex) {
        }
    }

    /**
     * stops/pauses the time
     */
    public void stop() {
        try {
            timeCounter.stop();
        } catch (Exception ex) {
        }
    }

    /**
     * resets the time to 0
     */
    public void reset() {
        seconds = startSeconds;
        minutes = startMinutes;
        hours = startHours;
        time.setText("Zeit: " + hours + ":" + minutes + ":" + seconds);
    }

    /**
     * @return return of an Time and that changes every secound, if the Timer is
     * started
     */
    public JLabel getChanginglabel() {
        return time;
    }

    /**
     * @return gives back the actual time of the timer in seconds
     */
    public int getTimeSec() {
        return seconds + (((hours * 60) + minutes) * 60);
    }
}
