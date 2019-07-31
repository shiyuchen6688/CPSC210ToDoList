package model.exceptions;

public class TaskAlreadyExistException extends Exception {
    public TaskAlreadyExistException() {}
    public TaskAlreadyExistException(String msg) {super(msg);}
}
