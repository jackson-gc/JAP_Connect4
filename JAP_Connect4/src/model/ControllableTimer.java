/******************************************
 * Controllable Timer class
 * Written by Daniel Cormier,
 * for use in CST8221
 *
 * See attached documentation for usage notes.
 *********************************************/

package model;
//If you have packages, put this in the model.

import java.awt.EventQueue;

import panelComponents.SystemPanel;

/**
 * ControllableTimer: A timer that can be started and stopped.
 * 
 * @author Daniel Cormier
 * @version 1.1
 * @since 1.8_301
 */
public class ControllableTimer extends Thread {
    /** Resumes operation. */
    static final int START = 1;

    /** Pauses operation */
    static final int STOP = 2;

    /** Resets timer value. */
    static final int RESET = 3;

    /** For terminating this thread for a clean shutdown */
    static final int TERMINATE = 4;


    /**
     * Timer status defaults in start position
     */
    private int status = START;
    
    /**
     * Timer total elapsed time
     */
    private int elapsed = 0;

    /**
     * This method permits external control of the timer. Send ControllableTimer.START
     * as the parameter to resume timing. ControllableTimer.STOP to pause timing.
     * ControllableTimer.RESET to reset the timer to 0.
     * 
     * @param cmd The control parameter, with values indicated above.
     */

    /**
     * Constructor requires reference to a View class. You may need to edit it. This
     * is a reference to your class that displays the timer.
     */
    private SystemPanel view;

    /**
     * Constructs a new ControllableTimer object with a reference to the SystemPanel
     * view.
     * 
     * @param view the SystemPanel instance used to display the timer
     */
    public ControllableTimer(SystemPanel view) {
        this.view = view;
        view.updateTimer(0);
    }

    /**
     * Method through which the timer is controlled. Timer starts on by default.
     * 
     * @param cmd START: Starts timer. STOP: Pauses timer. RESET: Sets timer back to
     *            0. TERMINATE: Kills this thread for a clean shutdown.
     */
    public synchronized void setStatus(int cmd) {
        switch (cmd) {
        case START:
            status = START;
            notify();
            break;
        case STOP:
            status = STOP;
            break;
        case RESET:
            view.updateTimer(0);
            elapsed = 0;
            break;
        case TERMINATE:
            status = TERMINATE;
        }
    }

    /**
     * Permits checking the status of the timer. Mostly intended for internal use.
     * 
     * @return status of the program in operation (START, STOP, TERMINATE)
     */
    public synchronized int getStatus() {
        if (status == STOP) {
            try {
                wait(); // wait can only be called from a synchronized method.
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return status;
    }

    /**
     * Gets the current elapsed time of the timer.
     * 
     * @return elapsed time in seconds
     */
    public synchronized int getTime() {
        return elapsed;
    }

    /**
     * The timer's main loop.
     */
    public void run() {
        while (getStatus() != TERMINATE) {
            try {
                sleep(1000); // Waits for 1000 milliseconds
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Threadsafe code to modify your UI. Injects operations into the UI thread.
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    // setTime method is part of your code. Either modify this, or add an appropriate
                    // convenience method.
                    view.updateTimer(++elapsed);
                }
            }); // End EventQueue
        }
    }
}
