package duke.exception;

import static duke.print.Print.printOneLine;
import static duke.print.Print.println;

public class DeadlineException extends Exception {
    public DeadlineException() {
        printOneLine();
        println("    Oops!!! Please remember to include the description and deadline of the task");
        println("    Please do so in the following manner:");
        println("");
        println("    deadline [description] /by [when]");
        println("");
        println("    THANKS!");
        printOneLine();
    }
}
