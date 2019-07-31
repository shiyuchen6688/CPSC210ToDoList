package model;

public class NoDueDateException extends Exception {
    public NoDueDateException() {
    }

    public NoDueDateException(String msg) {
        super(msg);
    }
}
