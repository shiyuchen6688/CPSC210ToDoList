package ui;

import java.util.Observable;
import java.util.Observer;

public class AddTaskCounter implements Observer {
    private int addTaskButtonCounter;

    public AddTaskCounter() {
        this.addTaskButtonCounter = 0;
    }
    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        addTaskButtonCounter++;
        System.out.println(arg + "This is the " + addTaskButtonCounter + " times");
    }
}
