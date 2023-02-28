package duke.exception;

import static duke.print.Print.*;

public class EventException extends Exception{

    public EventException() {
        printOneLine();
        println("    Oops!!! Please remember to include the description and duration of the event");
        println("    Please do so in the following manner:");
        println("");
        println("    event [description] /from [when] /to [when]");
        println("");
        println("    THANKS!");
        printOneLine();
    }
}
