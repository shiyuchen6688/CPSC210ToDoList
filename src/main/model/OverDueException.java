package model;

public class OverDueException extends Exception {
    public OverDueException() {
    }

    public OverDueException(String msg) {
        super(msg);
    }
}
