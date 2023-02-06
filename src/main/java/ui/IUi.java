package ui;

import java.util.ArrayList;

public interface IUi {
    /**
     * Prints system message from Duke to display for user.
     * @param message
     */
    public void printSystemMessage(String message);
    /**
     * Prints system message from Duke to display for user. Parameter is a generic
     * array with supported toString method.
     * @param <T> generic
     * @param list
     */
    public <T> void printSystemMessage(ArrayList<T> list);
    /**
     * Prints an error message to user
     * @param message
     */
    public void printSystemErrorMessage(String message);
    /**
     * Default duke greeting
     */
    public void greet();
    /**
     * Default duke bye bye
     */
    public void bye();
}
