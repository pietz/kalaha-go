import javafx.animation.AnimationTimer;

/**
 * The Timer manages the time delay between the refresh of buttons.
 */
public class Timer extends AnimationTimer {

    private final int speed;
    private long lap;
    private int stones;
    private static TimerListener listener;

    public Timer(int speed, TimerListener listener) {
        this.speed = speed;
        Timer.listener = listener;
    }

    @Override
    public void handle(long now) {
        int wait = 200000000; // The amount of time waiting between each animation step in ns
        if (now > (lap + wait*speed)) {
            if (stones <= 0) {
                stop();
                listener.timerFinished();
            } else {
                lap += (wait*speed);
                listener.timerUpdate();
                --stones;
            }
        }
    }

    public void go(int stones) {
        this.stones = stones;
        this.lap = System.nanoTime();
        listener.timerStarted();
        super.start();
    }


}
