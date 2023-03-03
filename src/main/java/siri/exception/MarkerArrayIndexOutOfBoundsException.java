package siri.exception;

/**
 * Signals an error caused by not entering task number to be marked or unmarked.
 */
public class MarkerArrayIndexOutOfBoundsException extends Exception {

    public String printError(){
        String errorMessage = "Please enter the task number that you would like to mark / unmark, " +
                "in the following format: " +
                "\nFor example if you want to mark / unmark task 2 as " +
                "done / undone: mark 2 / unmark 2";
        return errorMessage;
    }
}
