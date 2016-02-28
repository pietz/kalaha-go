/**
 * The TimerListener is used to call methods inside the GameUIController
 * from within the Timer class.
 */
public interface TimerListener {

    void timerStarted();
    void timerFinished();
    void timerUpdate();
}
