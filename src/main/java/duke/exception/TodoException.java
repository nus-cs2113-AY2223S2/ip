package duke.exception;

import static duke.print.Print.*;

public class TodoException extends Exception{

    public TodoException() {
        printOneLine();
        println("    OOPS!!! The description of a todo cannot be empty.");
        println("    Please enter a todo task in the following manner: ");
        println("");
        println("    todo [insert task description here]");
        println("");
        println("    THANKS!");

        printOneLine();
    }

}
